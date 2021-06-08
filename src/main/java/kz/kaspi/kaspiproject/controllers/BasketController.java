package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.entities.BasketItem;
import kz.kaspi.kaspiproject.entities.Books;
import kz.kaspi.kaspiproject.entities.Orders;
import kz.kaspi.kaspiproject.entities.Users;
import kz.kaspi.kaspiproject.services.BasketService;
import kz.kaspi.kaspiproject.services.BooksService;
import kz.kaspi.kaspiproject.services.OrdersService;
import kz.kaspi.kaspiproject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    BasketService basketService;

    @Autowired
    UsersService usersService;

    @Autowired
    BooksService booksService;

    @Autowired
    OrdersService ordersService;

    @GetMapping
    public String basket(Model model) {

        Users user = getCurrentUser();

        List<BasketItem> basketItems = basketService.findAllByUser(user);
        model.addAttribute("items", basketItems);

        List<Long> basketItemIds = new ArrayList<>();

        for (BasketItem basketItem: basketItems) {
            if (basketItem.isActive()) {
                basketItemIds.add(basketItem.getId());
            }
        }

        model.addAttribute("ids", basketItemIds);

        return "users/basket";
    }

    @GetMapping("/remove")
    public String removeItem(@RequestParam(value = "quantity") int quantity,
                             @RequestParam(value = "book") String bookName,
                             HttpServletRequest httpServletRequest) {

        Books book = booksService.findByName(bookName);

        Users user = getCurrentUser();

        BasketItem basketItem = basketService.findByBookAndUserAndActive(book, user, true);

        book.setQuantity(book.getQuantity() + quantity);
        booksService.save(book);

        user.getBasketItems().remove(basketItem);

        book.getBasketItems().remove(basketItem);

        basketService.deleteById(basketItem.getId());

        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam(value = "list") List<Long> basketItemIds,
                           HttpServletRequest httpServletRequest) {

        Users user = getCurrentUser();

        ordersService.save(new Orders(user, basketItemIds));

        for (long id: basketItemIds) {
            BasketItem basketItem = basketService.findById(id);
            if (basketItem != null) {
                basketItem.setActive(false);
                basketService.save(basketItem);
            }
        }

        return "redirect:" + httpServletRequest.getHeader("Referer");
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
