package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.entities.Authors;
import kz.kaspi.kaspiproject.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    AuthorsService authorsService;

//    @GetMapping("/{id}")
//    public Authors findById(@PathVariable int id, Model model) {
//        return authorsService.findById(id);
//    }

    @GetMapping
    public String list(Model model) {
        List<Authors> authors = authorsService.findAll();

        model.addAttribute("authors", authors);

        return "list-authors";
    }
}
