package kz.kaspi.kaspiproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/signup/**", "/books", "/authors", "/sections").permitAll()
                .antMatchers("/authors/new", "/authors/{id}", "/authors/delete/{id}").hasAuthority("admin")
                .antMatchers("/sections/new", "/sections/{id}", "/sections/delete/{id}").hasAuthority("admin")
                .antMatchers("/books/new", "/books/{id}", "/books/delete/{id}").hasAuthority("admin")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                anyRequest().authenticated().and()
                .formLogin().permitAll().loginPage("/signup/login").
                loginProcessingUrl("/login").
                usernameParameter("username").
                passwordParameter("password").
                defaultSuccessUrl("/", true).and()
                .exceptionHandling().accessDeniedPage("/error").and()
                .logout().logoutUrl("/logout").
                logoutSuccessUrl("/").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}