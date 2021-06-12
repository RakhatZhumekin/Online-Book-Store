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

    public Books save(Books book) {
        return booksRepository.save(book);
    }

    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    public List<Books> findAllByDeletedFalse() { return booksRepository.findAllByDeletedFalse(); }

    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    public Books findByName(String name) { return booksRepository.findByNameIgnoreCase(name); }

    public List<Books> findByNameContaining(String name) { return booksRepository.findByNameContainingIgnoreCaseAndDeletedFalse(name); }

    public List<Books> findAllBySection(Sections section) { return booksRepository.findAllBySectionAndDeletedFalse(section); }

    public List<Books> findAllByAuthor(Authors author) { return booksRepository.findAllByAuthorAndDeletedFalse(author); }

    public List<Books> findAllBySectionAndAuthor(Sections section, Authors author) { return booksRepository.findAllBySectionAndAuthorAndDeletedFalse(section, author); }

    public List<Books> findAllByLanguage(Language language) { return booksRepository.findAllByLanguageAndDeletedFalse(language); }

    public List<Books> findAllByStatus(Status status) { return booksRepository.findAllByStatusAndDeletedFalse(status); }

    public List<Books> findAllByPriceBetween(int from, int to) { return booksRepository.findAllByPriceBetweenAndDeletedFalse(from, to); }

    public List<Books> findAllByPriceGreaterThanEqual(int from) { return booksRepository.findAllByPriceGreaterThanEqualAndDeletedFalse(from); }

    public List<Books> findAllByPriceLessThanEqual(int to) { return booksRepository.findAllByPriceLessThanEqualAndDeletedFalse(to); }

    public List<Books> findAllBySectionAndLanguage(Sections section, Language language) { return booksRepository.findAllBySectionAndLanguageAndDeletedFalse(section, language); }

    public List<Books> findAllBySectionAndStatus(Sections section, Status status) { return booksRepository.findAllBySectionAndStatusAndDeletedFalse(section, status); }

    public List<Books> findAllBySectionAndPriceBetween(Sections section, int from, int to) { return booksRepository.findAllBySectionAndPriceBetweenAndDeletedFalse(section, from, to); }

    public List<Books> findAllBySectionAndPriceGreaterThanEqual(Sections section, int from) { return booksRepository.findAllBySectionAndPriceGreaterThanEqualAndDeletedFalse(section, from); }

    public List<Books> findAllBySectionAndPriceLessThanEqual(Sections section, int to) { return booksRepository.findAllBySectionAndPriceLessThanEqualAndDeletedFalse(section, to); }

    public List<Books> findAllByAuthorAndLanguage(Authors author, Language language) { return booksRepository.findAllByAuthorAndLanguageAndDeletedFalse(author, language); }

    public List<Books> findAllByAuthorAndStatus(Authors author, Status status) { return booksRepository.findAllByAuthorAndStatusAndDeletedFalse(author, status); }

    public List<Books> findAllByAuthorAndPriceBetween(Authors author, int from, int to) { return booksRepository.findAllByAuthorAndPriceBetweenAndDeletedFalse(author, from, to); }

    public List<Books> findAllByAuthorAndPriceGreaterThanEqual(Authors author, int from) { return booksRepository.findAllByAuthorAndPriceGreaterThanEqualAndDeletedFalse(author, from); }

    public List<Books> findAllByAuthorAndPriceLessThanEqual(Authors author, int to) { return booksRepository.findAllByAuthorAndPriceLessThanEqualAndDeletedFalse(author, to); }

    public List<Books> findAllByLanguageAndStatus(Language language, Status status) { return booksRepository.findAllByLanguageAndStatusAndDeletedFalse(language, status); }

    public List<Books> findAllByLanguageAndPriceBetween(Language language, int from, int to) { return booksRepository.findAllByLanguageAndPriceBetweenAndDeletedFalse(language, from, to); }

    public List<Books> findAllByLanguageAndPriceGreaterThanEqual(Language language, int from) { return booksRepository.findAllByLanguageAndPriceGreaterThanEqualAndDeletedFalse(language, from);}

    public List<Books> findAllByLanguageAndPriceLessThanEqual(Language language, int to) { return booksRepository.findAllByLanguageAndPriceLessThanEqualAndDeletedFalse(language, to);}

    public List<Books> findAllByStatusAndPriceBetween(Status status, int from, int to) { return booksRepository.findAllByStatusAndPriceBetweenAndDeletedFalse(status, from, to);}

    public List<Books> findAllByStatusAndPriceGreaterThanEqual(Status status, int from) { return booksRepository.findAllByStatusAndPriceGreaterThanEqualAndDeletedFalse(status, from);}

    public List<Books> findAllByStatusAndPriceLessThanEqual(Status status, int from) { return booksRepository.findAllByStatusAndPriceLessThanEqualAndDeletedFalse(status, from); }

    public List<Books> findAllBySectionAndAuthorAndLanguage(Sections section, Authors author, Language language) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndDeletedFalse(section, author, language); }

    public List<Books> findAllBySectionAndAuthorAndStatus(Sections section, Authors author, Status status) { return booksRepository.findAllBySectionAndAuthorAndStatusAndDeletedFalse(section, author, status); }

    public List<Books> findAllBySectionAndAuthorAndPriceBetween(Sections section, Authors author, int from, int to) { return booksRepository.findAllBySectionAndAuthorAndPriceBetweenAndDeletedFalse(section, author, from, to); }

    public List<Books> findAllBySectionAndAuthorAndPriceGreaterThanEqual(Sections section, Authors author, int from) { return  booksRepository.findAllBySectionAndAuthorAndPriceGreaterThanEqualAndDeletedFalse(section, author, from); }

    public List<Books> findAllBySectionAndAuthorAndPriceLessThanEqual(Sections section, Authors author, int to) { return booksRepository.findAllBySectionAndAuthorAndPriceLessThanEqualAndDeletedFalse(section, author, to); }

    public List<Books> findAllBySectionAndLanguageAndStatus(Sections section, Language language, Status status) { return booksRepository.findAllBySectionAndLanguageAndStatusAndDeletedFalse(section, language, status); }

    public List<Books> findAllBySectionAndLanguageAndPriceBetween(Sections section, Language language, int from, int to) { return booksRepository.findAllBySectionAndLanguageAndPriceBetweenAndDeletedFalse(section, language, from, to); }

    public List<Books> findAllBySectionAndLanguageAndPriceGreaterThanEqual(Sections section, Language language, int from) { return  booksRepository.findAllBySectionAndLanguageAndPriceGreaterThanEqualAndDeletedFalse(section, language, from); }

    public List<Books> findAllBySectionAndLanguageAndPriceLessThanEqual(Sections section, Language language, int to) { return booksRepository.findAllBySectionAndLanguageAndPriceLessThanEqualAndDeletedFalse(section, language, to); }

    public List<Books> findAllBySectionAndStatusAndPriceBetween(Sections section, Status status, int from, int to) { return booksRepository.findAllBySectionAndStatusAndPriceBetweenAndDeletedFalse(section, status, from, to); }

    public List<Books> findAllBySectionAndStatusAndPriceGreaterThanEqual(Sections section, Status status, int from) { return booksRepository.findAllBySectionAndStatusAndPriceGreaterThanEqualAndDeletedFalse(section, status, from); }

    public List<Books> findAllBySectionAndStatusAndPriceLessThanEqual(Sections section, Status status, int to) { return booksRepository.findAllBySectionAndStatusAndPriceLessThanEqualAndDeletedFalse(section, status, to); }

    public List<Books> findAllByAuthorAndLanguageAndStatus(Authors author, Language language, Status status) { return booksRepository.findAllByAuthorAndLanguageAndStatusAndDeletedFalse(author, language, status); }

    public List<Books> findAllByAuthorAndLanguageAndPriceBetween(Authors author, Language language, int from, int to) { return booksRepository.findAllByAuthorAndLanguageAndPriceBetweenAndDeletedFalse(author, language, from, to); }

    public List<Books> findAllByAuthorAndLanguageAndPriceGreaterThanEqual(Authors author, Language language, int from) { return booksRepository.findAllByAuthorAndLanguageAndPriceGreaterThanEqualAndDeletedFalse(author, language, from); }

    public List<Books> findAllByAuthorAndLanguageAndPriceLessThanEqual(Authors author, Language language, int to) { return booksRepository.findAllByAuthorAndLanguageAndPriceLessThanEqualAndDeletedFalse(author, language, to); }

    public List<Books> findAllByAuthorAndStatusAndPriceBetween(Authors author, Status status, int from, int to) { return booksRepository.findAllByAuthorAndStatusAndPriceBetweenAndDeletedFalse(author, status, from, to); }

    public List<Books> findAllByAuthorAndStatusAndPriceGreaterThanEqual(Authors author, Status status, int from) { return booksRepository.findAllByAuthorAndStatusAndPriceGreaterThanEqualAndDeletedFalse(author, status, from); }

    public List<Books> findAllByAuthorAndStatusAndPriceLessThanEqual(Authors author, Status status, int to) { return booksRepository.findAllByAuthorAndStatusAndPriceLessThanEqualAndDeletedFalse(author, status, to); }

    public List<Books> findAllByLanguageAndStatusAndPriceBetween(Language language, Status status, int from, int to) { return booksRepository.findAllByLanguageAndStatusAndPriceBetweenAndDeletedFalse(language, status, from, to); }

    public List<Books> findAllByLanguageAndStatusAndPriceGreaterThanEqual(Language language, Status status, int from) { return booksRepository.findAllByLanguageAndStatusAndPriceGreaterThanEqualAndDeletedFalse(language, status, from); }

    public List<Books> findAllByLanguageAndStatusAndPriceLessThanEqual(Language language, Status status, int to) { return booksRepository.findAllByLanguageAndStatusAndPriceLessThanEqualAndDeletedFalse(language, status, to); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatus(Sections section, Authors author, Language language, Status status) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatusAndDeletedFalse(section, author, language, status); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceBetween(Sections section, Authors author, Language language, int from, int to) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndPriceBetweenAndDeletedFalse(section, author, language, from, to); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceGreaterThanEqual(Sections section, Authors author, Language language, int from) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndPriceGreaterThanEqualAndDeletedFalse(section, author, language, from); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceLessThanEqual(Sections section, Authors author, Language language, int to) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndPriceLessThanEqualAndDeletedFalse(section, author, language, to); }

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceBetween(Sections section, Authors author, Status status, int from, int to) { return booksRepository.findAllBySectionAndAuthorAndStatusAndPriceBetweenAndDeletedFalse(section, author, status, from, to); }

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceGreaterThanEqual(Sections section, Authors author, Status status, int from) { return booksRepository.findAllBySectionAndAuthorAndStatusAndPriceGreaterThanEqualAndDeletedFalse(section, author, status, from); }

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceLessThanEqual(Sections section, Authors author, Status status, int to) { return booksRepository.findAllBySectionAndAuthorAndStatusAndPriceLessThanEqualAndDeletedFalse(section, author, status, to); }

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceBetween(Sections section, Language language, Status status, int from, int to) { return booksRepository.findAllBySectionAndLanguageAndStatusAndPriceBetweenAndDeletedFalse(section, language, status, from, to); }

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceGreaterThanEqual(Sections section, Language language, Status status, int from) { return booksRepository.findAllBySectionAndLanguageAndStatusAndPriceGreaterThanEqualAndDeletedFalse(section, language, status, from); }

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceLessThanEqual(Sections section, Language language, Status status, int to) { return booksRepository.findAllBySectionAndLanguageAndStatusAndPriceLessThanEqualAndDeletedFalse(section, language, status, to); }

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceBetween(Authors author, Language language, Status status, int from, int to) { return booksRepository.findAllByAuthorAndLanguageAndStatusAndPriceBetweenAndDeletedFalse(author, language, status, from, to); }

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(Authors author, Language language, Status status, int from) { return booksRepository.findAllByAuthorAndLanguageAndStatusAndPriceGreaterThanEqualAndDeletedFalse(author, language, status, from); }

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceLessThanEqual(Authors author, Language language, Status status, int to) { return booksRepository.findAllByAuthorAndLanguageAndStatusAndPriceLessThanEqualAndDeletedFalse(author, language, status, to); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceBetween(Sections section, Authors author, Language language, Status status, int from, int to) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceBetweenAndDeletedFalse(section, author, language, status, from, to); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(Sections section, Authors author, Language language, Status status, int from) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceGreaterThanEqualAndDeletedFalse(section, author, language, status, from); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceLessThanEqual(Sections section, Authors author, Language language, Status status, int to) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatusAndPriceLessThanEqualAndDeletedFalse(section, author, language, status, to); }
}
