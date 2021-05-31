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

    public List<Books> findAllBySectionAndAuthor(Sections section, Authors author);

    public List<Books> findAllBySectionAndLanguage(Sections section, Language language);

    public List<Books> findAllBySectionAndStatus(Sections section, Status status);

    public List<Books> findAllByAuthorAndLanguage(Authors author, Language language);

    public List<Books> findAllByAuthorAndStatus(Authors author, Status status);

    public List<Books> findAllByLanguageAndStatus(Language language, Status status);

    public List<Books> findAllBySectionAndAuthorAndLanguage(Sections section, Authors author, Language language);

    public List<Books> findAllBySectionAndAuthorAndStatus(Sections section, Authors author, Status status);

    public List<Books> findAllBySectionAndLanguageAndStatus(Sections section, Language language, Status status);

    public List<Books> findAllByAuthorAndLanguageAndStatus(Authors author, Language language, Status status);

    public List<Books> findAllBySectionAndAuthorAndLanguageAndStatus(Sections section, Authors author, Language language, Status status);
}