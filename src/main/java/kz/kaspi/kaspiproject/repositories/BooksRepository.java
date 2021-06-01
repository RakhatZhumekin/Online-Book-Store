package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Authors;
import kz.kaspi.kaspiproject.entities.Books;
import kz.kaspi.kaspiproject.entities.Books.Language;
import kz.kaspi.kaspiproject.entities.Books.Status;
import kz.kaspi.kaspiproject.entities.Sections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

    public Books findByNameIgnoreCase(String name);

    public List<Books> findAllBySection(Sections section);

    public List<Books> findAllByAuthor(Authors author);

    public List<Books> findAllByLanguage(Language language);

    public List<Books> findAllByStatus(Status status);

    public List<Books> findAllByPriceBetween(int from, int to);

    public List<Books> findAllByPriceGreaterThanEqual(int from);

    public List<Books> findAllByPriceLessThanEqual(int to);

    public List<Books> findAllBySectionAndAuthor(Sections section, Authors author);

    public List<Books> findAllBySectionAndLanguage(Sections section, Language language);

    public List<Books> findAllBySectionAndStatus(Sections section, Status status);

    public List<Books> findAllBySectionAndPriceBetween(Sections section, int from, int to);

    public List<Books> findAllBySectionAndPriceGreaterThanEqual(Sections section, int from);

    public List<Books> findAllBySectionAndPriceLessThanEqual(Sections section, int to);

    public List<Books> findAllByAuthorAndLanguage(Authors author, Language language);

    public List<Books> findAllByAuthorAndStatus(Authors author, Status status);

    public List<Books> findAllByAuthorAndPriceBetween(Authors author, int from, int to);

    public List<Books> findAllByAuthorAndPriceGreaterThanEqual(Authors author, int from);

    public List<Books> findAllByAuthorAndPriceLessThanEqual(Authors author, int to);

    public List<Books> findAllByLanguageAndStatus(Language language, Status status);

    public List<Books> findAllByLanguageAndPriceBetween(Language language, int from, int to);

    public List<Books> findAllByLanguageAndPriceGreaterThanEqual(Language language, int from);

    public List<Books> findAllByLanguageAndPriceLessThanEqual(Language language, int to);

    public List<Books> findAllByStatusAndPriceBetween(Status status, int from, int to);

    public List<Books> findAllByStatusAndPriceGreaterThanEqual(Status status, int from);

    public List<Books> findAllByStatusAndPriceLessThanEqual(Status status, int from);

    public List<Books> findAllBySectionAndAuthorAndLanguage(Sections section, Authors author, Language language);

    public List<Books> findAllBySectionAndAuthorAndStatus(Sections section, Authors author, Status status);

    public List<Books> findAllBySectionAndAuthorAndPriceBetween(Sections section, Authors author, int from, int to);

    public List<Books> findAllBySectionAndAuthorAndPriceGreaterThanEqual(Sections section, Authors author, int from);

    public List<Books> findAllBySectionAndAuthorAndPriceLessThanEqual(Sections section, Authors author, int to);

    public List<Books> findAllBySectionAndLanguageAndStatus(Sections section, Language language, Status status);

    public List<Books> findAllBySectionAndLanguageAndPriceBetween(Sections section, Language language, int from, int to);

    public List<Books> findAllBySectionAndLanguageAndPriceGreaterThanEqual(Sections section, Language language, int from);

    public List<Books> findAllBySectionAndLanguageAndPriceLessThanEqual(Sections section, Language language, int to);

    public List<Books> findAllBySectionAndStatusAndPriceBetween(Sections section, Status status, int from, int to);

    public List<Books> findAllBySectionAndStatusAndPriceGreaterThanEqual(Sections section, Status status, int from);

    public List<Books> findAllBySectionAndStatusAndPriceLessThanEqual(Sections section, Status status, int to);

    public List<Books> findAllByAuthorAndLanguageAndStatus(Authors author, Language language, Status status);

    public List<Books> findAllByAuthorAndLanguageAndPriceBetween(Authors author, Language language, int from, int to);

    public List<Books> findAllByAuthorAndLanguageAndPriceGreaterThanEqual(Authors author, Language language, int from);

    public List<Books> findAllByAuthorAndLanguageAndPriceLessThanEqual(Authors author, Language language, int to);

    public List<Books> findAllByAuthorAndStatusAndPriceBetween(Authors author, Status status, int from, int to);

    public List<Books> findAllByAuthorAndStatusAndPriceGreaterThanEqual(Authors author, Status status, int from);

    public List<Books> findAllByAuthorAndStatusAndPriceLessThanEqual(Authors author, Status status, int to);

    public List<Books> findAllByLanguageAndStatusAndPriceBetween(Language language, Status status, int from, int to);

    public List<Books> findAllByLanguageAndStatusAndPriceGreaterThanEqual(Language language, Status status, int from);

    public List<Books> findAllByLanguageAndStatusAndPriceLessThanEqual(Language language, Status status, int to);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatus(Sections section, Authors author, Language language, Status status);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceBetween(Sections section, Authors author, Language language, int from, int to);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceGreaterThanEqual(Sections section, Authors author, Language language, int from);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceLessThanEqual(Sections section, Authors author, Language language, int to);

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceBetween(Sections section, Authors author, Status status, int from, int to);

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceGreaterThanEqual(Sections section, Authors author, Status status, int from);

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceLessThanEqual(Sections section, Authors author, Status status, int to);

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceBetween(Sections section, Language language, Status status, int from, int to);

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceGreaterThanEqual(Sections section, Language language, Status status, int from);

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceLessThanEqual(Sections section, Language language, Status status, int to);

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceBetween(Authors author, Language language, Status status, int from, int to);

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(Authors author, Language language, Status status, int from);

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceLessThanEqual(Authors author, Language language, Status status, int to);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceBetween(Sections section, Authors author, Language language, Status status, int from, int to);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceGreaterThanEqual(Sections section, Authors author, Language language, Status status, int from);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceLessThanEqual(Sections section, Authors author, Language language, Status status, int to);
}