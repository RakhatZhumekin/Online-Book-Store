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
import java.util.ArrayList;
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
    public String list(@RequestParam(value = "section", required = false) String sectionName,
                       @RequestParam(value = "author", required = false) String authorName,
                       @RequestParam(value = "language", required = false) Language language,
                       @RequestParam(value = "status", required = false) Status status,
                       Model model) {
        return returnList(sectionName, authorName, language, status, model);
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

        return returnList(null, null, null, null, model);
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
        return returnList(null, null, null, null, model);
    }

    private String returnList(String sectionName, String authorName, Language language, Status status, Model model) {
        List<Books> books = booksService.findAll();
        StringBuilder title = new StringBuilder("All Books");

        if (status != null) {
            if (status == Status.AVAILABLE || status == Status.SOLD) {
                title = new StringBuilder("All " + status.getName() + " Books");
                books = booksService.findAllByStatus(status);
            }
            else {
                model.addAttribute("description", "Error accessing given status");
                model.addAttribute("cause", "The status with the name " + status + " does not exist");
                return "books/error";
            }
        }

        if (sectionName != null) {
            if (sectionsService.findByName(sectionName) != null) {
                if (status != null) {
                    books = booksService.findAllBySectionAndStatus(sectionsService.findByName(sectionName),
                            status);
                }
                else {
                    books = booksService.findAllBySection(sectionsService.findByName(sectionName));
                }
                title.append(" from section " + sectionName);
            }
            else {
                model.addAttribute("description", "Error accessing given section");
                model.addAttribute("cause", "The section with the name " + sectionName + " does not exist");
                return "books/error";
            }
        }

        if (authorName != null){
            if (authorsService.findByName(authorName) != null) {
                if (sectionName != null) {
                    if (status != null) {
                        books = booksService.findAllBySectionAndAuthorAndStatus(sectionsService.findByName(sectionName),
                                authorsService.findByName(authorName), status);
                    }
                    else {
                        books = booksService.findAllBySectionAndAuthor(sectionsService.findByName(sectionName), authorsService.findByName(authorName));
                    }
                    title.append(" and from author " + authorName);
                }
                else {
                    if (status != null) {
                        books = booksService.findAllByAuthorAndStatus(authorsService.findByName(authorName),
                                status);
                    }
                    else {
                        books = booksService.findAllByAuthor(authorsService.findByName(authorName));
                    }

                    title.append(" from author " + authorName);
                }
            }
            else {
                model.addAttribute("description", "Error accessing given author");
                model.addAttribute("cause", "The author with the name " + authorName + " does not exist");
                return "books/error";
            }
        }

        if (language != null) {
            if (language == Language.ENGLISH || language == Language.KAZAKH || language == Language.RUSSIAN) {
                title.append(" in " + language.getName());
                if (authorName != null) {
                    if (sectionName != null) {
                        if (status != null) {
                            books = booksService.findAllBySectionAndAuthorAndLanguageAndStatus(sectionsService.findByName(sectionName),
                                    authorsService.findByName(authorName), language, status);
                        }
                        else {
                            books = booksService.findAllBySectionAndAuthorAndLanguage(sectionsService.findByName(sectionName),
                                    authorsService.findByName(authorName), language);
                        }
                    }
                    else {
                        if (status != null) {
                            books = booksService.findAllByAuthorAndLanguageAndStatus(authorsService.findByName(authorName),
                                    language, status);
                        }
                        else {
                            books = booksService.findAllByAuthorAndLanguage(authorsService.findByName(authorName), language);
                        }
                    }
                }
                else if (sectionName != null) {
                    if (status != null) {
                        books = booksService.findAllBySectionAndLanguageAndStatus(sectionsService.findByName(sectionName),
                                language, status);
                    }
                    books = booksService.findAllBySectionAndLanguage(sectionsService.findByName(sectionName), language);
                }
                else {
                    if (status != null) {
                        books = booksService.findAllByLanguageAndStatus(language, status);
                    }
                    else {
                        books = booksService.findAllByLanguage(language);
                    }
                }
            }
            else {
                model.addAttribute("description", "Error accessing given language");
                model.addAttribute("cause", "The language with the name " + language + " does not exist");
                return "books/error";
            }
        }

        model.addAttribute("title", title.toString());
        model.addAttribute("books", books);
        return "books/list";
    }
}