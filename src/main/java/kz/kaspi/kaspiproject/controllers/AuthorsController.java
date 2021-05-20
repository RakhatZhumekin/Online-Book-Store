package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.dto.AuthorsDTO;
import kz.kaspi.kaspiproject.entities.Authors;
import kz.kaspi.kaspiproject.services.AuthorsService;
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
    public String save(@Valid @ModelAttribute("author") AuthorsDTO authorsDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "authors/new";
        }

        authorsService.save(new Authors(authorsDTO.getName(), authorsDTO.getBirthday()));

        return "redirect:authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable int id, Model model) {
        Authors author = authorsService.findById(id);
        if (author == null) {
            return "authors/error";
        }

        authorsService.deleteById(id);
        return returnList(model);
    }

    @GetMapping("/update")
    public String update(@Valid @ModelAttribute("author") AuthorsDTO authorsDTO, Model model) {
        Authors currentAuthor = authorsService.findById(authorsDTO.getId());

        String name = authorsDTO.getName();
        LocalDate birthday = authorsDTO.getBirthday();

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
