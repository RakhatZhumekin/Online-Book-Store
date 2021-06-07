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

    public List<Books> findByNameContainingIgnoreCaseAndDeletedFalse(String name);

    public List<Books> findAllByDeletedFalse();

    public List<Books> findAllBySectionAndDeletedFalse(Sections section);

    public List<Books> findAllByAuthorAndDeletedFalse(Authors author);

    public List<Books> findAllByLanguageAndDeletedFalse(Language language);

    public List<Books> findAllByStatusAndDeletedFalse(Status status);

    public List<Books> findAllByPriceBetweenAndDeletedFalse(int from, int to);

    public List<Books> findAllByPriceGreaterThanEqualAndDeletedFalse(int from);

    public List<Books> findAllByPriceLessThanEqualAndDeletedFalse(int to);

    public List<Books> findAllBySectionAndAuthorAndDeletedFalse(Sections section, Authors author);

    public List<Books> findAllBySectionAndLanguageAndDeletedFalse(Sections section, Language language);

    public List<Books> findAllBySectionAndStatusAndDeletedFalse(Sections section, Status status);

    public List<Books> findAllBySectionAndPriceBetweenAndDeletedFalse(Sections section, int from, int to);

    public List<Books> findAllBySectionAndPriceGreaterThanEqualAndDeletedFalse(Sections section, int from);

    public List<Books> findAllBySectionAndPriceLessThanEqualAndDeletedFalse(Sections section, int to);

    public List<Books> findAllByAuthorAndLanguageAndDeletedFalse(Authors author, Language language);

    public List<Books> findAllByAuthorAndStatusAndDeletedFalse(Authors author, Status status);

    public List<Books> findAllByAuthorAndPriceBetweenAndDeletedFalse(Authors author, int from, int to);

    public List<Books> findAllByAuthorAndPriceGreaterThanEqualAndDeletedFalse(Authors author, int from);

    public List<Books> findAllByAuthorAndPriceLessThanEqualAndDeletedFalse(Authors author, int to);

    public List<Books> findAllByLanguageAndStatusAndDeletedFalse(Language language, Status status);

    public List<Books> findAllByLanguageAndPriceBetweenAndDeletedFalse(Language language, int from, int to);

    public List<Books> findAllByLanguageAndPriceGreaterThanEqualAndDeletedFalse(Language language, int from);

    public List<Books> findAllByLanguageAndPriceLessThanEqualAndDeletedFalse(Language language, int to);

    public List<Books> findAllByStatusAndPriceBetweenAndDeletedFalse(Status status, int from, int to);

    public List<Books> findAllByStatusAndPriceGreaterThanEqualAndDeletedFalse(Status status, int from);

    public List<Books> findAllByStatusAndPriceLessThanEqualAndDeletedFalse(Status status, int from);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndDeletedFalse(Sections section, Authors author, Language language);

    public List<Books> findAllBySectionAndAuthorAndStatusAndDeletedFalse(Sections section, Authors author, Status status);

    public List<Books> findAllBySectionAndAuthorAndPriceBetweenAndDeletedFalse(Sections section, Authors author, int from, int to);

    public List<Books> findAllBySectionAndAuthorAndPriceGreaterThanEqualAndDeletedFalse(Sections section, Authors author, int from);

    public List<Books> findAllBySectionAndAuthorAndPriceLessThanEqualAndDeletedFalse(Sections section, Authors author, int to);

    public List<Books> findAllBySectionAndLanguageAndStatusAndDeletedFalse(Sections section, Language language, Status status);

    public List<Books> findAllBySectionAndLanguageAndPriceBetweenAndDeletedFalse(Sections section, Language language, int from, int to);

    public List<Books> findAllBySectionAndLanguageAndPriceGreaterThanEqualAndDeletedFalse(Sections section, Language language, int from);

    public List<Books> findAllBySectionAndLanguageAndPriceLessThanEqualAndDeletedFalse(Sections section, Language language, int to);

    public List<Books> findAllBySectionAndStatusAndPriceBetweenAndDeletedFalse(Sections section, Status status, int from, int to);

    public List<Books> findAllBySectionAndStatusAndPriceGreaterThanEqualAndDeletedFalse(Sections section, Status status, int from);

    public List<Books> findAllBySectionAndStatusAndPriceLessThanEqualAndDeletedFalse(Sections section, Status status, int to);

    public List<Books> findAllByAuthorAndLanguageAndStatusAndDeletedFalse(Authors author, Language language, Status status);

    public List<Books> findAllByAuthorAndLanguageAndPriceBetweenAndDeletedFalse(Authors author, Language language, int from, int to);

    public List<Books> findAllByAuthorAndLanguageAndPriceGreaterThanEqualAndDeletedFalse(Authors author, Language language, int from);

    public List<Books> findAllByAuthorAndLanguageAndPriceLessThanEqualAndDeletedFalse(Authors author, Language language, int to);

    public List<Books> findAllByAuthorAndStatusAndPriceBetweenAndDeletedFalse(Authors author, Status status, int from, int to);

    public List<Books> findAllByAuthorAndStatusAndPriceGreaterThanEqualAndDeletedFalse(Authors author, Status status, int from);

    public List<Books> findAllByAuthorAndStatusAndPriceLessThanEqualAndDeletedFalse(Authors author, Status status, int to);

    public List<Books> findAllByLanguageAndStatusAndPriceBetweenAndDeletedFalse(Language language, Status status, int from, int to);

    public List<Books> findAllByLanguageAndStatusAndPriceGreaterThanEqualAndDeletedFalse(Language language, Status status, int from);

    public List<Books> findAllByLanguageAndStatusAndPriceLessThanEqualAndDeletedFalse(Language language, Status status, int to);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndDeletedFalse(Sections section, Authors author, Language language, Status status);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceBetweenAndDeletedFalse(Sections section, Authors author, Language language, int from, int to);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceGreaterThanEqualAndDeletedFalse(Sections section, Authors author, Language language, int from);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndPriceLessThanEqualAndDeletedFalse(Sections section, Authors author, Language language, int to);

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceBetweenAndDeletedFalse(Sections section, Authors author, Status status, int from, int to);

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceGreaterThanEqualAndDeletedFalse(Sections section, Authors author, Status status, int from);

    public List<Books> findAllBySectionAndAuthorAndStatusAndPriceLessThanEqualAndDeletedFalse(Sections section, Authors author, Status status, int to);

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceBetweenAndDeletedFalse(Sections section, Language language, Status status, int from, int to);

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceGreaterThanEqualAndDeletedFalse(Sections section, Language language, Status status, int from);

    public List<Books> findAllBySectionAndLanguageAndStatusAndPriceLessThanEqualAndDeletedFalse(Sections section, Language language, Status status, int to);

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceBetweenAndDeletedFalse(Authors author, Language language, Status status, int from, int to);

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceGreaterThanEqualAndDeletedFalse(Authors author, Language language, Status status, int from);

    public List<Books> findAllByAuthorAndLanguageAndStatusAndPriceLessThanEqualAndDeletedFalse(Authors author, Language language, Status status, int to);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceBetweenAndDeletedFalse(Sections section, Authors author, Language language, Status status, int from, int to);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceGreaterThanEqualAndDeletedFalse(Sections section, Authors author, Language language, Status status, int from);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatusAndPriceLessThanEqualAndDeletedFalse(Sections section, Authors author, Language language, Status status, int to);
}