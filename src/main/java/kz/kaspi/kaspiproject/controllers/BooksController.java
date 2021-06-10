package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.dto.BooksDTO;
import kz.kaspi.kaspiproject.entities.*;
import kz.kaspi.kaspiproject.entities.Books.Language;
import kz.kaspi.kaspiproject.entities.Books.Status;
import kz.kaspi.kaspiproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    BasketService basketService;

    @Autowired
    UsersService usersService;

    @GetMapping
    public String list(@RequestParam(value = "section", required = false) String sectionName,
                       @RequestParam(value = "author", required = false) String authorName,
                       @RequestParam(value = "language", required = false) Language language,
                       @RequestParam(value = "status", required = false) Status status,
                       @RequestParam(value = "from", required = false) String from,
                       @RequestParam(value = "to", required = false) String to,
                       @RequestParam(value = "name", required = false) String name, Model model) {

        if (getCurrentUser() != null) {
            if (getCurrentUser().getRole().getName().equals("admin")) {
                return returnListAdmin(model);
            }
        }

        if (name != null) {
            if (!name.isBlank()) {
                return returnListByName(name, model);
            }
        }

        if (from == null) {
            if (to == null) {
                return returnList(sectionName, authorName, language, status, null, null, model);
            }
            else {
                if (to.isEmpty()) {
                    return returnList(sectionName, authorName, language, status, null, null, model);
                }
                else {
                    return returnList(sectionName, authorName, language, status, null, to, model);
                }
            }
        }
        else if (to == null) {
            if (from.isEmpty()) {
                return returnList(sectionName, authorName, language, status, null, null, model);
            }
            else {
                return returnList(sectionName, authorName, language, status, from, null, model);
            }
        }

        if (from.isEmpty()) {
            if (to.isEmpty()) {
                return returnList(sectionName, authorName, language, status, null, null, model);
            } else {
                return returnList(sectionName, authorName, language, status, null, to, model);
            }
        }
        else {
            if (to.isEmpty()) {
                return returnList(sectionName, authorName, language, status, from, null, model);
            }
            else {
                System.out.println("Both from and to are given");
                return returnList(sectionName, authorName, language, status, from, to, model);
            }
        }
    }

    @GetMapping("/add")
    public String addToCart(@RequestParam(value = "quantity") int quantity,
                          @RequestParam(value = "name") String name, HttpServletRequest httpServletRequest) {

        Books book = booksService.findByName(name);

        if (!book.isDeleted()) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

            Users user = usersService.findByName(username);

            BasketItem currentItem = basketService.findByBookAndUserAndActive(book, user, true);
            if (currentItem != null) {
                currentItem.setQuantity(currentItem.getQuantity() + quantity);
                basketService.save(currentItem);
            } else {
                basketService.save(new BasketItem(user, book, quantity));
            }

            book.setQuantity(book.getQuantity() - quantity);
            booksService.save(book);
        }

        return "redirect:" + httpServletRequest.getHeader("Referer");
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
        if (book == null || book.isDeleted()) {
            model.addAttribute("description", "Error finding the book");
            model.addAttribute("cause", "The book with the given id does not exist");
            return "authors/error";
        }

        if (getCurrentUser() == null) {
            model.addAttribute("book", book);
            return "books/details";
        }
        else if (getCurrentUser().getRole().getName().equals("user")) {
            model.addAttribute("book", book);
            return "books/details";
        }
        else {
            BooksDTO booksDTO = new BooksDTO();
            booksDTO.setId(book.getId());
            booksDTO.setName(book.getName());
            booksDTO.setAuthor(book.getAuthor());
            booksDTO.setSection(book.getSection());
            booksDTO.setLanguage(book.getLanguage());
            booksDTO.setPrice(book.getPrice());
            booksDTO.setQuantity(book.getQuantity());
            booksDTO.setDescription(book.getDescription());

            model.addAttribute("book", booksDTO);
            return "books/update";
        }
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("book") BooksDTO booksDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        if (booksService.findByName(booksDTO.getName()) != null) {
            model.addAttribute("description", "Error creating the book");
            model.addAttribute("cause", "The book with the given name already exists in the database");
            return "books/error";
        }

        Status status;

        if (booksDTO.getQuantity() == 0)
            status = Status.SOLD;
        else
            status = Status.AVAILABLE;

        booksService.save(new Books(booksDTO.getName(), booksDTO.getAuthor(), booksDTO.getSection(),
                booksDTO.getLanguage(), booksDTO.getPrice(), booksDTO.getQuantity(), status, booksDTO.getDescription()));

        return "redirect:books";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam(value = "id") int id, Model model) {
        Books book = booksService.findById(id);
        if (book == null || book.isDeleted()) {
            model.addAttribute("description", "Error deleting the book");
            model.addAttribute("cause", "The book with the id has already been deleted");
            return "books/error";
        }

        book.setDeleted(true);

        booksService.save(book);

        return returnListAdmin(model);
    }

    @GetMapping("/restore")
    public String restoreById(@RequestParam(value = "id") int id, Model model) {
        Books book = booksService.findById(id);

        if (!book.isDeleted()) {
            model.addAttribute("description", "Error restoring the book");
            model.addAttribute("cause", "The book with the given id isn't deleted");
            return "books/error";
        }

        book.setDeleted(false);

        booksService.save(book);

        return returnListAdmin(model);
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
        String description = booksDTO.getDescription();

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
        currentBook.setDescription(description);

        booksService.save(currentBook);
        return returnListAdmin(model);
    }

    private String returnList(String sectionName, String authorName, Language language, Status status, String from, String to, Model model) {
        List<Books> books = booksService.findAllByDeletedFalse();
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
                    else {
                        books = booksService.findAllBySectionAndLanguage(sectionsService.findByName(sectionName), language);
                    }
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

        if (from != null) {
            if (!from.isEmpty()) {
                if (Integer.parseInt(from) > 0) {
                    title.append(" from " + from + " KZT");
                    if (to != null) {
                        if (!to.isEmpty()) {
                            if (Integer.parseInt(to) > 0 && Integer.parseInt(from) <= Integer.parseInt(to)) {
                                title.append(" up to " + to + " KZT");
                                if (status != null) {
                                    if (language != null) {
                                        if (authorName != null) {
                                            if (sectionName != null) {
                                                books = booksService.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceBetween(sectionsService.findByName(sectionName),
                                                        authorsService.findByName(authorName), language, status, Integer.parseInt(from), Integer.parseInt(to));
                                            } else {
                                                books = booksService.findAllByAuthorAndLanguageAndStatusAndPriceBetween(authorsService.findByName(authorName),
                                                        language, status, Integer.parseInt(from), Integer.parseInt(to));
                                            }
                                        } else {
                                            if (sectionName != null) {
                                                books = booksService.findAllBySectionAndLanguageAndStatusAndPriceBetween(sectionsService.findByName(sectionName),
                                                        language, status, Integer.parseInt(from), Integer.parseInt(to));
                                            } else {
                                                books = booksService.findAllByLanguageAndStatusAndPriceBetween(language, status, Integer.parseInt(from), Integer.parseInt(to));
                                            }
                                        }
                                    } else {
                                        if (authorName != null) {
                                            if (sectionName != null) {
                                                books = booksService.findAllBySectionAndAuthorAndStatusAndPriceBetween(sectionsService.findByName(sectionName),
                                                        authorsService.findByName(authorName), status, Integer.parseInt(from), Integer.parseInt(to));
                                            } else {
                                                books = booksService.findAllByAuthorAndStatusAndPriceBetween(authorsService.findByName(authorName),
                                                        status, Integer.parseInt(from), Integer.parseInt(to));
                                            }
                                        } else {
                                            if (sectionName != null) {
                                                books = booksService.findAllBySectionAndStatusAndPriceBetween(sectionsService.findByName(sectionName),
                                                        status, Integer.parseInt(from), Integer.parseInt(to));
                                            } else {
                                                books = booksService.findAllByStatusAndPriceBetween(status, Integer.parseInt(from), Integer.parseInt(to));
                                            }
                                        }
                                    }
                                } else {
                                    if (language != null) {
                                        if (authorName != null) {
                                            if (sectionName != null) {
                                                books = booksService.findAllBySectionAndAuthorAndLanguageAndPriceBetween(sectionsService.findByName(sectionName),
                                                        authorsService.findByName(authorName), language, Integer.parseInt(from), Integer.parseInt(to));
                                            } else {
                                                books = booksService.findAllByAuthorAndLanguageAndPriceBetween(authorsService.findByName(authorName),
                                                        language, Integer.parseInt(from), Integer.parseInt(to));
                                            }
                                        } else {
                                            if (sectionName != null) {
                                                books = booksService.findAllBySectionAndLanguageAndPriceBetween(sectionsService.findByName(sectionName),
                                                        language, Integer.parseInt(from), Integer.parseInt(to));
                                            } else {
                                                books = booksService.findAllByLanguageAndPriceBetween(language, Integer.parseInt(from), Integer.parseInt(to));
                                            }
                                        }
                                    } else {
                                        if (authorName != null) {
                                            if (sectionName != null) {
                                                books = booksService.findAllBySectionAndAuthorAndPriceBetween(sectionsService.findByName(sectionName),
                                                        authorsService.findByName(authorName), Integer.parseInt(from), Integer.parseInt(to));
                                            } else {
                                                books = booksService.findAllByAuthorAndPriceBetween(authorsService.findByName(authorName),
                                                        Integer.parseInt(from), Integer.parseInt(to));
                                            }
                                        } else {
                                            if (sectionName != null) {
                                                books = booksService.findAllBySectionAndPriceBetween(sectionsService.findByName(sectionName),
                                                        Integer.parseInt(from), Integer.parseInt(to));
                                            } else {
                                                books = booksService.findAllByPriceBetween(Integer.parseInt(from), Integer.parseInt(to));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (status != null) {
                            if (language != null) {
                                if (authorName != null) {
                                    if (sectionName != null) {
                                        books = booksService.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(sectionsService.findByName(sectionName),
                                                authorsService.findByName(authorName), language, status, Integer.parseInt(from));
                                    } else {
                                        books = booksService.findAllByAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(authorsService.findByName(authorName),
                                                language, status, Integer.parseInt(from));
                                    }
                                } else {
                                    if (sectionName != null) {
                                        books = booksService.findAllBySectionAndLanguageAndStatusAndPriceGreaterThanEqual(sectionsService.findByName(sectionName),
                                                language, status, Integer.parseInt(from));
                                    } else {
                                        books = booksService.findAllByLanguageAndStatusAndPriceGreaterThanEqual(language, status, Integer.parseInt(from));
                                    }
                                }
                            } else {
                                if (authorName != null) {
                                    if (sectionName != null) {
                                        books = booksService.findAllBySectionAndAuthorAndStatusAndPriceGreaterThanEqual(sectionsService.findByName(sectionName),
                                                authorsService.findByName(authorName), status, Integer.parseInt(from));
                                    } else {
                                        books = booksService.findAllByAuthorAndStatusAndPriceGreaterThanEqual(authorsService.findByName(authorName),
                                                status, Integer.parseInt(from));
                                    }
                                } else {
                                    if (sectionName != null) {
                                        books = booksService.findAllBySectionAndStatusAndPriceGreaterThanEqual(sectionsService.findByName(sectionName),
                                                status, Integer.parseInt(from));
                                    } else {
                                        books = booksService.findAllByStatusAndPriceGreaterThanEqual(status, Integer.parseInt(from));
                                    }
                                }
                            }
                        } else {
                            if (language != null) {
                                if (authorName != null) {
                                    if (sectionName != null) {
                                        books = booksService.findAllBySectionAndAuthorAndLanguageAndPriceGreaterThanEqual(sectionsService.findByName(sectionName),
                                                authorsService.findByName(authorName), language, Integer.parseInt(from));
                                    } else {
                                        books = booksService.findAllByAuthorAndLanguageAndPriceGreaterThanEqual(authorsService.findByName(authorName),
                                                language, Integer.parseInt(from));
                                    }
                                } else {
                                    if (sectionName != null) {
                                        books = booksService.findAllBySectionAndLanguageAndPriceGreaterThanEqual(sectionsService.findByName(sectionName),
                                                language, Integer.parseInt(from));
                                    } else {
                                        books = booksService.findAllByLanguageAndPriceGreaterThanEqual(language, Integer.parseInt(from));
                                    }
                                }
                            } else {
                                if (authorName != null) {
                                    if (sectionName != null) {
                                        books = booksService.findAllBySectionAndAuthorAndPriceGreaterThanEqual(sectionsService.findByName(sectionName),
                                                authorsService.findByName(authorName), Integer.parseInt(from));
                                    } else {
                                        books = booksService.findAllByAuthorAndPriceGreaterThanEqual(authorsService.findByName(authorName),
                                                Integer.parseInt(from));
                                    }
                                } else {
                                    if (sectionName != null) {
                                        books = booksService.findAllBySectionAndPriceGreaterThanEqual(sectionsService.findByName(sectionName),
                                                Integer.parseInt(from));
                                    } else {
                                        books = booksService.findAllByPriceGreaterThanEqual(Integer.parseInt(from));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (to != null) {
            if (!to.isEmpty()) {
                if (Integer.parseInt(to) > 0) {
                    title.append(" up to " + to + " KZT");
                    if (status != null) {
                        if (language != null) {
                            if (authorName != null) {
                                if (sectionName != null) {
                                    books = booksService.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceLessThanEqual(sectionsService.findByName(sectionName),
                                            authorsService.findByName(authorName), language, status, Integer.parseInt(to));
                                } else {
                                    books = booksService.findAllByAuthorAndLanguageAndStatusAndPriceLessThanEqual(authorsService.findByName(authorName),
                                            language, status, Integer.parseInt(to));
                                }
                            } else {
                                if (sectionName != null) {
                                    books = booksService.findAllBySectionAndLanguageAndStatusAndPriceLessThanEqual(sectionsService.findByName(sectionName),
                                            language, status, Integer.parseInt(to));
                                } else {
                                    books = booksService.findAllByLanguageAndStatusAndPriceLessThanEqual(language, status, Integer.parseInt(to));
                                }
                            }
                        } else {
                            if (authorName != null) {
                                if (sectionName != null) {
                                    books = booksService.findAllBySectionAndAuthorAndStatusAndPriceLessThanEqual(sectionsService.findByName(sectionName),
                                            authorsService.findByName(authorName), status, Integer.parseInt(to));
                                } else {
                                    books = booksService.findAllByAuthorAndStatusAndPriceLessThanEqual(authorsService.findByName(authorName),
                                            status, Integer.parseInt(to));
                                }
                            } else {
                                if (sectionName != null) {
                                    books = booksService.findAllBySectionAndStatusAndPriceLessThanEqual(sectionsService.findByName(sectionName),
                                            status, Integer.parseInt(to));
                                } else {
                                    books = booksService.findAllByStatusAndPriceLessThanEqual(status, Integer.parseInt(to));
                                }
                            }
                        }
                    } else {
                        if (language != null) {
                            if (authorName != null) {
                                if (sectionName != null) {
                                    books = booksService.findAllBySectionAndAuthorAndLanguageAndPriceLessThanEqual(sectionsService.findByName(sectionName),
                                            authorsService.findByName(authorName), language, Integer.parseInt(to));
                                } else {
                                    books = booksService.findAllByAuthorAndLanguageAndPriceLessThanEqual(authorsService.findByName(authorName),
                                            language, Integer.parseInt(to));
                                }
                            } else {
                                if (sectionName != null) {
                                    books = booksService.findAllBySectionAndLanguageAndPriceLessThanEqual(sectionsService.findByName(sectionName),
                                            language, Integer.parseInt(to));
                                } else {
                                    books = booksService.findAllByLanguageAndPriceLessThanEqual(language, Integer.parseInt(to));
                                }
                            }
                        } else {
                            if (authorName != null) {
                                if (sectionName != null) {
                                    books = booksService.findAllBySectionAndAuthorAndPriceLessThanEqual(sectionsService.findByName(sectionName),
                                            authorsService.findByName(authorName), Integer.parseInt(to));
                                } else {
                                    books = booksService.findAllByAuthorAndPriceLessThanEqual(authorsService.findByName(authorName),
                                            Integer.parseInt(to));
                                }
                            } else {
                                if (sectionName != null) {
                                    books = booksService.findAllBySectionAndPriceLessThanEqual(sectionsService.findByName(sectionName),
                                            Integer.parseInt(to));
                                } else {
                                    books = booksService.findAllByPriceLessThanEqual(Integer.parseInt(to));
                                }
                            }
                        }
                    }
                }
            }
        }

        model.addAttribute("title", title.toString());
        model.addAttribute("books", books);
        return "books/list";
    }

    private String returnListByName(String name, Model model) {
        List<Books> books = booksService.findByNameContaining(name);
        model.addAttribute("title", "Result for '" + name + "'");
        model.addAttribute("books", books);
        return "books/list";
    }

    private String returnListAdmin(Model model) {
        List<Books> books = booksService.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }

    private Users getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return usersService.findByName(username);
    }
}