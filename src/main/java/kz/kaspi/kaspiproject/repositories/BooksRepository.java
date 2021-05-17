package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {

}
