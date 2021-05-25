package kz.kaspi.kaspiproject.services;

import kz.kaspi.kaspiproject.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersService.findByName(username);

        if (user == null)
            throw new UsernameNotFoundException("User " + username + " not found");

        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        System.out.println(user.getRole().getName());

        return new User(user.getName(), user.getPassword(), simpleGrantedAuthorities);
    }
}
