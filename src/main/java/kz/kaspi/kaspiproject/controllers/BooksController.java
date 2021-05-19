package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.entities.Books;
import kz.kaspi.kaspiproject.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    @GetMapping
    public String list(Model model) {
        List<Books> books = booksService.findAll();

        model.addAttribute("books", books);

        return "books/list-books";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        return "books/new";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable int id, Model model) {
        return "books/update";
    }
}
