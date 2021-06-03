package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.entities.BasketItem;
import kz.kaspi.kaspiproject.entities.Users;
import kz.kaspi.kaspiproject.services.BasketService;
import kz.kaspi.kaspiproject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    BasketService basketService;

    @Autowired
    UsersService usersService;

    @GetMapping
    public String basket(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Users user = usersService.findByName(username);

        List<BasketItem> basketItems = basketService.findAllByUser(user);
        model.addAttribute("items", basketItems);

        return "users/basket";
    }
}
