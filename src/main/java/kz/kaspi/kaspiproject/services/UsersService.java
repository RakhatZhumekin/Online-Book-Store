package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.Users;
import kz.kaspi.kaspiproject.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findById(long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users findByName(String name) {
        return usersRepository.findByNameIgnoreCase(name);
    }

    public void save(Users user) {
        usersRepository.save(user);
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public void deleteById(long id) {
        usersRepository.deleteById(id);
    }
}
