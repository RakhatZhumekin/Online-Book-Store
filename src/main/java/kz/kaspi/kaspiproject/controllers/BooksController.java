package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.dto.BooksDTO;
import kz.kaspi.kaspiproject.entities.Authors;
import kz.kaspi.kaspiproject.entities.Books;
import kz.kaspi.kaspiproject.entities.Books.Language;
import kz.kaspi.kaspiproject.entities.Books.Status;
import kz.kaspi.kaspiproject.entities.Sections;
import kz.kaspi.kaspiproject.services.AuthorsService;
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
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    @Autowired
    AuthorsService authorsService;

    @Autowired
    SectionsService sectionsService;

    @GetMapping
    public String list(Model model) {
        return returnList(model);
    }

    @ModelAttribute("allAuthors")
    public List<Authors> getAuthors() {
        return authorsService.findAll();
    }

    @ModelAttribute("allSections")
    public List<Sections> getSections() {
        return sectionsService.findAll();
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("book", new BooksDTO());
        return "books/new";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable int id, Model model) {
        Books book = booksService.findById(id);
        if (book == null) {
            model.addAttribute("description", "Error finding the book");
            model.addAttribute("cause", "The book with the given id does not exist");
            return "authors/error";
        }

        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setId(book.getId());
        booksDTO.setName(book.getName());
        booksDTO.setAuthor(book.getAuthor());
        booksDTO.setSection(book.getSection());
        booksDTO.setLanguage(book.getLanguage());
        booksDTO.setPrice(book.getPrice());
        booksDTO.setQuantity(book.getQuantity());

        model.addAttribute("book", booksDTO);
        return "books/update";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("book") BooksDTO booksDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        if (booksService.findByName(booksDTO.getName()) != null) {
            model.addAttribute("description", "Error creating the book");
            model.addAttribute("cause", "The book with the given name already exists");
            return "books/error";
        }

        Status status;

        if (booksDTO.getQuantity() == 0)
            status = Status.SOLD;
        else
            status = Status.AVAILABLE;

        booksService.save(new Books(booksDTO.getName(), booksDTO.getAuthor(), booksDTO.getSection(),
                booksDTO.getLanguage(), booksDTO.getPrice(), booksDTO.getQuantity(), status));

        return "redirect:books";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam(value = "id") int id, Model model) {
        Books book = booksService.findById(id);
        if (book == null) {
            model.addAttribute("description", "Error deleting the book");
            model.addAttribute("cause", "The book with the given id does not exist");
            return "books/error";
        }

        Authors author = book.getAuthor();
        Sections section = book.getSection();

        if (author != null)
            author.getBooks().remove(book);

        if (section != null)
            section.getBooks().remove(book);

        booksService.deleteById(id);

        return returnList(model);
    }

    @GetMapping("/update")
    public String update(@Valid @ModelAttribute("book") BooksDTO booksDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "books/update";
        }

        Books currentBook = booksService.findById(booksDTO.getId());

        String name = booksDTO.getName();
        Authors author = booksDTO.getAuthor();
        Sections section = booksDTO.getSection();
        Language language = booksDTO.getLanguage();
        int price = booksDTO.getPrice();
        int quantity = booksDTO.getQuantity();

        Status status;

        if (quantity == 0)
            status = Status.SOLD;
        else
            status = Status.AVAILABLE;

        if (booksService.findByName(name) != null && !booksService.findByName(name).equals(currentBook)) {
            model.addAttribute("description", "Error updating the book");
            model.addAttribute("cause", "Another book with such name already exists");
            return "books/error";
        }

        currentBook.setName(name);
        currentBook.setAuthor(author);
        currentBook.setSection(section);
        currentBook.setLanguage(language);
        currentBook.setPrice(price);
        currentBook.setQuantity(quantity);
        currentBook.setStatus(status);

        booksService.save(currentBook);
        return returnList(model);
    }

    private String returnList(Model model) {
        List<Books> books = booksService.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }
}