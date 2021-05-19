package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.Authors;
import kz.kaspi.kaspiproject.repositories.AuthorsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsService {

    private AuthorsRepository authorsRepository;

    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Authors findById(int id) {
        return authorsRepository.findById(id).orElse(null);
    }

    public void save(Authors author) { authorsRepository.save(author); }

    public List<Authors> findAll() {
        return authorsRepository.findAll();
    }

    public void deleteById(int id) {
        authorsRepository.deleteById(id);
    }
}