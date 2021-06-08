package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.dto.AuthorsDTO;
import kz.kaspi.kaspiproject.entities.Authors;
import kz.kaspi.kaspiproject.services.AuthorsService;
import kz.kaspi.kaspiproject.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    AuthorsService authorsService;

    @Autowired
    BooksService booksService;

    @GetMapping
    public String list(Model model) {
        return returnList(model);
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("author", new AuthorsDTO());
        return "authors/new";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable int id, Model model) {
        Authors author = authorsService.findById(id);
        if (author == null) {
            model.addAttribute("description", "Error finding the author");
            model.addAttribute("cause", "The author with the given id does not exist");
            return "authors/error";
        }

        AuthorsDTO authorsDTO = new AuthorsDTO();
        authorsDTO.setId(author.getId());
        authorsDTO.setName(author.getName());
        authorsDTO.setBirthday(author.getBirthday());

        model.addAttribute("author", authorsDTO);
        return "authors/update";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("author") AuthorsDTO authorsDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "authors/new";
        }

        if (authorsService.findByName(authorsDTO.getName()) != null) {
            model.addAttribute("description", "Error creating the author");
            model.addAttribute("cause", "The author with the given name already exists");
            return "authors/error";
        }

        authorsService.save(new Authors(authorsDTO.getName(), authorsDTO.getBirthday()));

        return "redirect:authors";
    }

    @GetMapping("/update")
    public String update(@Valid @ModelAttribute("author") AuthorsDTO authorsDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "authors/update";
        }

        Authors currentAuthor = authorsService.findById(authorsDTO.getId());

        String name = authorsDTO.getName();
        LocalDate birthday = authorsDTO.getBirthday();

        if (authorsService.findByName(name) != null && !authorsService.findByName(name).equals(currentAuthor)) {
            model.addAttribute("description", "Error updating the author");
            model.addAttribute("cause", "Another author with such name already exists");
            return "authors/error";
        }

        currentAuthor.setName(name);
        currentAuthor.setBirthday(birthday);

        authorsService.save(currentAuthor);
        return returnList(model);
    }

    private String returnList(Model model) {
        List<Authors> authors = authorsService.findAll();
        model.addAttribute("authors", authors);
        return "authors/list";
    }
}
