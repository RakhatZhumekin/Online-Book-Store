package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.dto.UsersDTO;
import kz.kaspi.kaspiproject.entities.Roles;
import kz.kaspi.kaspiproject.entities.Users;
import kz.kaspi.kaspiproject.services.RolesService;
import kz.kaspi.kaspiproject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class RegistrationController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("getRole")
    private Roles getRole() {
        return rolesService.findById(2);
    }

    @GetMapping
    public String registrationForm(Model model) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setRole(getRole());
        model.addAttribute("user", usersDTO);
        return "users/register";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("user") UsersDTO usersDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Oops");
            return "users/register";
        }

        if (usersService.findByName(usersDTO.getName()) != null)
            return "users/error";

        Users user = new Users();
        user.setName(usersDTO.getName());
        user.setRole(usersDTO.getRole());
        user.setPassword(passwordEncoder.encode(usersDTO.getPassword()));

        usersService.save(user);
        System.out.println(user.getPassword());

        return "index";
    }
}
