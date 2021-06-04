package kz.kaspi.kaspiproject.controllers;

import kz.kaspi.kaspiproject.dto.UsersDTO;
import kz.kaspi.kaspiproject.entities.Roles;
import kz.kaspi.kaspiproject.entities.Users;
import kz.kaspi.kaspiproject.services.RolesService;
import kz.kaspi.kaspiproject.services.SecurityService;
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
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService;

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
            return "users/register";
        }

        if (usersService.findByName(usersDTO.getName().trim()) != null)
            return "users/error";

        String decodedPassword = usersDTO.getPassword();

        Users user = new Users();
        user.setName(usersDTO.getName().trim());
        user.setRole(usersDTO.getRole());
        user.setPassword(passwordEncoder.encode(usersDTO.getPassword()));

        usersService.save(user);

        securityService.autoLogin(user.getName(), decodedPassword);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "users/login";
    }
}
