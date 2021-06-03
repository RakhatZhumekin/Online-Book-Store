package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.BasketItem;
import kz.kaspi.kaspiproject.entities.Books;
import kz.kaspi.kaspiproject.entities.Users;
import kz.kaspi.kaspiproject.repositories.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    private BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public void deleteById(long id) { basketRepository.deleteById(id); }

    public BasketItem findById(long id) { return basketRepository.findById(id).orElse(null); }

    public void save(BasketItem basketItem) { basketRepository.save(basketItem); }

    public List<BasketItem> findAllByUser(Users user) { return basketRepository.findAllByUser(user); }

    public BasketItem findByBookAndUser(Books book, Users user) { return basketRepository.findByBookAndUser(book, user); }

    public BasketItem findByBookAndUserAndActive(Books book, Users user, boolean active) { return basketRepository.findByBookAndUserAndActive(book, user, active); }
}
