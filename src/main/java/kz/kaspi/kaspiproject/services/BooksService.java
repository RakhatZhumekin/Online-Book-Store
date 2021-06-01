package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.Authors;
import kz.kaspi.kaspiproject.entities.Books;
import kz.kaspi.kaspiproject.entities.Books.Language;
import kz.kaspi.kaspiproject.entities.Books.Status;
import kz.kaspi.kaspiproject.entities.Sections;
import kz.kaspi.kaspiproject.repositories.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Books findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public void save(Books book) {
        booksRepository.save(book);
    }

    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    public Books findByName(String name) { return booksRepository.findByNameIgnoreCase(name); }

    public List<Books> findAllBySection(Sections section) { return booksRepository.findAllBySection(section); }

    public List<Books> findAllByAuthor(Authors author) { return booksRepository.findAllByAuthor(author); }

    public List<Books> findAllBySectionAndAuthor(Sections section, Authors author) { return booksRepository.findAllBySectionAndAuthor(section, author); }

    public List<Books> findAllByLanguage(Language language) { return booksRepository.findAllByLanguage(language); }

    public List<Books> findAllByStatus(Status status) { return booksRepository.findAllByStatus(status); }

    public List<Books> findAllByPriceBetween(int from, int to) { return booksRepository.findAllByPriceBetween(from, to); }

    public List<Books> findAllByPriceGreaterThanEqual(int from) { return booksRepository.findAllByPriceGreaterThanEqual(from); }

    public List<Books> findAllByPriceLessThanEqual(int to) { return booksRepository.findAllByPriceLessThanEqual(to); }

    public List<Books> findAllBySectionAndLanguage(Sections section, Language language) { return booksRepository.findAllBySectionAndLanguage(section, language); }

    public List<Books> findAllBySectionAndStatus(Sections section, Status status) { return booksRepository.findAllBySectionAndStatus(section, status); }

    public List<Books> findAllBySectionAndPriceBetween(Sections section, int from, int to) { return booksRepository.findAllBySectionAndPriceBetween(section, from, to); }

    public List<Books> findAllBySectionAndPriceGreaterThanEqual(Sections section, int from) { return booksRepository.findAllBySectionAndPriceGreaterThanEqual(section, from); }

    public List<Books> findAllBySectionAndPriceLessThanEqual(Sections section, int to) { return booksRepository.findAllBySectionAndPriceLessThanEqual(section, to); }

    public List<Books> findAllByAuthorAndLanguage(Authors author, Language language) { return booksRepository.findAllByAuthorAndLanguage(author, language); }

    public List<Books> findAllByAuthorAndStatus(Authors author, Status status) { return booksRepository.findAllByAuthorAndStatus(author, status); }

    public List<Books> findAllByAuthorAndPriceBetween(Authors author, int from, int to) { return booksRepository.findAllByAuthorAndPriceBetween(author, from, to); }

    public List<Books> findAllByAuthorAndPriceGreaterThanEqual(Authors author, int from) { return booksRepository.findAllByAuthorAndPriceGreaterThanEqual(author, from); }

    public List<Books> findAllByAuthorAndPriceLessThanEqual(Authors author, int to) { return booksRepository.findAllByAuthorAndPriceLessThanEqual(author, to); }

    public List<Books> findAllByLanguageAndStatus(Language language, Status status) { return booksRepository.findAllByLanguageAndStatus(language, status); }

    public List<Books> findAllByLanguageAndPriceBetween(Language language, int from, int to) { return booksRepository.findAllByLanguageAndPriceBetween(language, from, to); }

    public List<Books> findAllByLanguageAndPriceGreaterThanEqual(Language language, int from) { return booksRepository.findAllByLanguageAndPriceGreaterThanEqual(language, from);}

    public List<Books> findAllByLanguageAndPriceLessThanEqual(Language language, int to) { return booksRepository.findAllByLanguageAndPriceLessThanEqual(language, to);}

    public List<Books> findAllByStatusAndPriceBetween(Status status, int from, int to) { return booksRepository.findAllByStatusAndPriceBetween(status, from, to);}

    public List<Books> findAllByStatusAndPriceGreaterThanEqual(Status status, int from) { return booksRepository.findAllByStatusAndPriceGreaterThanEqual(status, from);}

    public List<Books> findAllByStatusAndPriceLessThanEqual(Status status, int from) { return booksRepository.findAllByStatusAndPriceLessThanEqual(status, from); }

    public List<Books> findAllBySectionAndAuthorAndLanguage(Sections section, Authors author, Language language) { return booksRepository.findAllBySectionAndAuthorAndLanguage(section, author, language); }

    public List<Books> findAllBySectionAndAuthorAndStatus(Sections section, Authors author, Status status) { return booksRepository.findAllBySectionAndAuthorAndStatus(section, author, status); }

    public List<Books> findAllBySectionAndAuthorAndPriceBetween(Sections section, Authors author, int from, int to) { return booksRepository.findAllBySectionAndAuthorAndPriceBetween(section, author, from, to); }

    public List<Books> findAllBySectionAndAuthorAndPriceGreaterThanEqual(Sections section, Authors author, int from) { return  booksRepository.findAllBySectionAndAuthorAndPriceGreaterThanEqual(section, author, from); }

    public List<Books> findAllBySectionAndAuthorAndPriceLessThanEqual(Sections section, Authors author, int to) { return booksRepository.findAllBySectionAndAuthorAndPriceLessThanEqual(section, author, to); }

    public List<Books> findAllBySectionAndLanguageAndStatus(Sections section, Language language, Status status) { return booksRepository.findAllBySectionAndLanguageAndStatus(section, language, status); }

    public List<Books> findAllBySectionAndLanguageAndPriceBetween(Sections section, Language language, int from, int to) { return booksRepository.findAllBySectionAndLanguageAndPriceBetween(section, language, from, to); }

    public List<Books> findAllBySectionAndLanguageAndPriceGreaterThanEqual(Sections section, Language language, int from) { return  booksRepository.findAllBySectionAndLanguageAndPriceGreaterThanEqual(section, language, from); }

    public List<Books> findAllBySectionAndLanguageAndPriceLessThanEqual(Sections section, Language language, int to) { return booksRepository.findAllBySectionAndLanguageAndPriceLessThanEqual(section, language, to); }

    public List<Books> findAllBySectionAndStatusAndPriceBetween(Sections section, Status status, int from, int to) { return booksRepository.findAllBySectionAndStatusAndPriceBetween(section, status, from, to); }

    public List<Books> findAllBySectionAndStatusAndPriceGreaterThanEqual(Sections section, Status status, int from) { return booksRepository.findAllBySectionAndStatusAndPriceGreaterThanEqual(section, status, from); }

    public List<Books> findAllBySectionAndStatusAndPriceLessThanEqual(Sections section, Status status, int to) { return booksRepository.findAllBySectionAndStatusAndPriceLessThanEqual(section, status, to); }

    public List<Books> findAllByAuthorAndLanguageAndStatus(Authors author, Language language, Status status) { return booksRepository.findAllByAuthorAndLanguageAndStatus(author, language, status); }

    public List<Books> findAllByAuthorAndLanguageAndPriceBetween(Authors author, Language language, int from, int to) { return booksRepository.findAllByAuthorAndLanguageAndPriceBetween(author, language, from, to); }

    public List<Books> findAllByAuthorAndLanguageAndPriceGreaterThanEqual(Authors author, Language language, int from) { return booksRepository.findAllByAuthorAndLanguageAndPriceGreaterThanEqual(author, language, from); }

    public List<Books> findAllByAuthorAndLanguageAndPriceLessThanEqual(Authors author, Language language, int to) { return booksRepository.findAllByAuthorAndLanguageAndPriceLessThanEqual(author, language, to); }

    public List<Books> findAllByAuthorAndStatusAndPriceBetween(Authors author, Status status, int from, int to) { return booksRepository.findAllByAuthorAndStatusAndPriceBetween(author, status, from, to); }

    public List<Books> findAllByAuthorAndStatusAndPriceGreaterThanEqual(Authors author, Status status, int from) { return booksRepository.findAllByAuthorAndStatusAndPriceGreaterThanEqual(author, status, from); }

    public List<Books> findAllByAuthorAndStatusAndPriceLessThanEqual(Authors author, Status status, int to) { return booksRepository.findAllByAuthorAndStatusAndPriceLessThanEqual(author, status, to); }

    public List<Books> findAllByLanguageAndStatusAndPriceBetween(Language language, Status status, int from, int to) { return booksRepository.findAllByLanguageAndStatusAndPriceBetween(language, status, from, to); }

    public List<Books> findAllByLanguageAndStatusAndPriceGreaterThanEqual(Language language, Status status, int from) { return booksRepository.findAllByLanguageAndStatusAndPriceGreaterThanEqual(language, status, from); }

    public List<Books> findAllByLanguageAndStatusAndPriceLessThanEqual(Language language, Status status, int to) { return booksRepository.findAllByLanguageAndStatusAndPriceLessThanEqual(language, status, to); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatus(Sections section, Authors author, Language language, Status status) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatus(section, author, language, status); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceBetween(Sections section, Authors author, Language language, int from, int to) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndPriceBetween(section, author, language, from, to); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceGreaterThanEqual(Sections section, Authors author, Language language, int from) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndPriceGreaterThanEqual(section, author, language, from); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceLessThanEqual(Sections section, Authors author, Language language, int to) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndPriceLessThanEqual(section, author, language, to); }

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceBetween(Sections section, Authors author, Status status, int from, int to) { return booksRepository.findAllBySectionAndAuthorAndStatusAndPriceBetween(section, author, status, from, to); }

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceGreaterThanEqual(Sections section, Authors author, Status status, int from) { return booksRepository.findAllBySectionAndAuthorAndStatusAndPriceGreaterThanEqual(section, author, status, from); }

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceLessThanEqual(Sections section, Authors author, Status status, int to) { return booksRepository.findAllBySectionAndAuthorAndStatusAndPriceLessThanEqual(section, author, status, to); }

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceBetween(Sections section, Language language, Status status, int from, int to) { return booksRepository.findAllBySectionAndLanguageAndStatusAndPriceBetween(section, language, status, from, to); }

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceGreaterThanEqual(Sections section, Language language, Status status, int from) { return booksRepository.findAllBySectionAndLanguageAndStatusAndPriceGreaterThanEqual(section, language, status, from); }

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceLessThanEqual(Sections section, Language language, Status status, int to) { return booksRepository.findAllBySectionAndLanguageAndStatusAndPriceLessThanEqual(section, language, status, to); }

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceBetween(Authors author, Language language, Status status, int from, int to) { return booksRepository.findAllByAuthorAndLanguageAndStatusAndPriceBetween(author, language, status, from, to); }

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(Authors author, Language language, Status status, int from) { return booksRepository.findAllByAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(author, language, status, from); }

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceLessThanEqual(Authors author, Language language, Status status, int to) { return booksRepository.findAllByAuthorAndLanguageAndStatusAndPriceLessThanEqual(author, language, status, to); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceBetween(Sections section, Authors author, Language language, Status status, int from, int to) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceBetween(section, author, language, status, from, to); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(Sections section, Authors author, Language language, Status status, int from) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(section, author, language, status, from); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceLessThanEqual(Sections section, Authors author, Language language, Status status, int to) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceLessThanEqual(section, author, language, status, to); }
}
