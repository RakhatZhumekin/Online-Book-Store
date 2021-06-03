package kz.kaspi.kaspiproject.repositories;

import kz.kaspi.kaspiproject.entities.Orders;
import kz.kaspi.kaspiproject.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    public List<Orders> findAllByUser(Users user);
}
