package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

}
