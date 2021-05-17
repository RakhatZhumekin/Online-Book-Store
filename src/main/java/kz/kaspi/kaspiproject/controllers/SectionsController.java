package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.entities.Sections;
import kz.kaspi.kaspiproject.services.SectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sections")
public class SectionsController {

    @Autowired
    SectionsService sectionsService;

    @GetMapping
    public String list(Model model) {
        List<Sections> sections = sectionsService.findAll();

        model.addAttribute("sections", sections);

        return "list-sections";
    }
}
