package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.entities.*;
import kz.kaspi.kaspiproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    UsersService usersService;

    @Autowired
    BasketService basketService;

    @Autowired
    BooksService booksService;

    @GetMapping
    public String ordersHistory(Model model) {
        List<Orders> orders = ordersService.findAllByUser(getCurrentUser());
        model.addAttribute("orders", orders);
        model.addAttribute("title", "Orders of " + getCurrentUser().getName());
        return "orders/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {
        Orders order = ordersService.findById(id);
        if (order != null && order.getUser().equals(getCurrentUser())) {

            List<Long> basketItemsIds = order.getBasketItemIds();

            List<BasketItem> basketItems = new ArrayList<>();

            int totalCost = 0;

            for (long basketItemId: basketItemsIds) {
                BasketItem basketItem = basketService.findById(basketItemId);
                if (basketItem != null) {
                    totalCost += (basketItem.getBook().getPrice() * basketItem.getQuantity());
                    basketItems.add(basketItem);
                }
            }

            model.addAttribute("title", "Details of order #" + id + " of " + getCurrentUser().getName());
            model.addAttribute("id", id);
            model.addAttribute("order", order);
            model.addAttribute("items", basketItems);
            model.addAttribute("cost", totalCost);

            return "orders/details";
        }
        else {
            model.addAttribute("id", id);
            return "orders/error";
        }
    }

    private Users getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return usersService.findByName(username);
    }
}
