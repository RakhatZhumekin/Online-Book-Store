package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.BasketItem;
import kz.kaspi.kaspiproject.entities.Books;
import kz.kaspi.kaspiproject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<BasketItem, Long> {

    public List<BasketItem> findAllByUser(Users user);

    public List<BasketItem> findAllByBookAndActiveTrue(Books book);

    public BasketItem findByBookAndUser(Books book, Users user);

    public BasketItem findByBookAndUserAndActive(Books book, Users user, boolean active);
}
