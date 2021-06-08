package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.Orders;
import kz.kaspi.kaspiproject.entities.Users;
import kz.kaspi.kaspiproject.repositories.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) { this.ordersRepository = ordersRepository; }

    public Orders findById(long id) { return ordersRepository.findById(id).orElse(null); }

    public List<Orders> findAllByUser(Users user) { return ordersRepository.findAllByUser(user); }

    public void save(Orders order) { ordersRepository.save(order); }
}
