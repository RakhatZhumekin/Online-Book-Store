package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.dto.SectionsDTO;
import kz.kaspi.kaspiproject.entities.Sections;
import kz.kaspi.kaspiproject.services.BooksService;
import kz.kaspi.kaspiproject.services.SectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/sections")
public class SectionsController {

    @Autowired
    SectionsService sectionsService;

    @Autowired
    BooksService booksService;

    @GetMapping
    public String list(Model model) {
        return returnList(model);
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("section", new SectionsDTO());
        return "sections/new";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable int id, Model model) {
        Sections section = sectionsService.findById(id);
        if (section == null) {
            model.addAttribute("description", "Error finding the section");
            model.addAttribute("cause", "The section with the given id does not exist");
            return "authors/error";
        }

        SectionsDTO sectionsDTO = new SectionsDTO();
        sectionsDTO.setId(section.getId());
        sectionsDTO.setName(section.getName());

        model.addAttribute("section", sectionsDTO);
        return "sections/update";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("section") SectionsDTO sectionsDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "sections/new";
        }

        if (sectionsService.findByName(sectionsDTO.getName()) != null) {
            model.addAttribute("description", "Error creating the section");
            model.addAttribute("cause", "The section with the given name already exists");
            return "sections/error";
        }

        sectionsService.save(new Sections(sectionsDTO.getName()));

        return "redirect:sections";
    }

    @GetMapping("/update")
    public String update(@Valid @ModelAttribute("section") SectionsDTO sectionsDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "sections/update";
        }

        Sections currentSection = sectionsService.findById(sectionsDTO.getId());

        String name = sectionsDTO.getName();

        if (sectionsService.findByName(sectionsDTO.getName()) != null && !sectionsService.findByName(sectionsDTO.getName()).equals(currentSection)) {
            model.addAttribute("description", "Error updating the section");
            model.addAttribute("cause", "Another section with such name already exists");
            return "sections/error";
        }

        currentSection.setName(name);

        sectionsService.save(currentSection);
        return returnList(model);
    }

    private String returnList(Model model) {
        List<Sections> sections = sectionsService.findAll();
        model.addAttribute("sections", sections);
        return "sections/list";
    }
}
