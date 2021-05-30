package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.Books;
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
}
