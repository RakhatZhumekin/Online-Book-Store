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

    public List<Books> findAllBySectionAndLanguage(Sections section, Language language) { return booksRepository.findAllBySectionAndLanguage(section, language); }

    public List<Books> findAllBySectionAndStatus(Sections section, Status status) { return booksRepository.findAllBySectionAndStatus(section, status); }

    public List<Books> findAllByAuthorAndLanguage(Authors author, Language language) { return booksRepository.findAllByAuthorAndLanguage(author, language); }

    public List<Books> findAllByAuthorAndStatus(Authors author, Status status) { return booksRepository.findAllByAuthorAndStatus(author, status); }

    public List<Books> findAllByLanguageAndStatus(Language language, Status status) { return booksRepository.findAllByLanguageAndStatus(language, status); }

    public List<Books> findAllBySectionAndAuthorAndLanguage(Sections section, Authors author, Language language) { return booksRepository.findAllBySectionAndAuthorAndLanguage(section, author, language); }

    public List<Books> findAllBySectionAndAuthorAndStatus(Sections section, Authors author, Status status) { return booksRepository.findAllBySectionAndAuthorAndStatus(section, author, status); }

    public List<Books> findAllBySectionAndLanguageAndStatus(Sections section, Language language, Status status) { return booksRepository.findAllBySectionAndLanguageAndStatus(section, language, status); }

    public List<Books> findAllByAuthorAndLanguageAndStatus(Authors author, Language language, Status status) { return booksRepository.findAllByAuthorAndLanguageAndStatus(author, language, status); }

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatus(Sections section, Authors author, Language language, Status status) { return booksRepository.findAllBySectionAndAuthorAndLanguageAndStatus(section, author, language, status); }
}
