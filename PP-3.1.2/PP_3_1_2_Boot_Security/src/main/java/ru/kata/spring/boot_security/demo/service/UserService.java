package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void saveUser(User user);

    void removeUserById(Long id);

    void updateUser(User user, Long id);

    UserDetails loadUserByUsername(String username);
}