package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Util.RoleValidator;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleValidator roleValidator;
    private final RoleService roleService;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleValidator roleValidator, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleValidator = roleValidator;
        this.roleService = roleService;
    }

    @Transactional
    public void register(User user, String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleValidator.addRole(user, role);
        List<Role> roles = roleService.getAllRoles();
        user.setRole(roles);
        userRepository.save(user);
    }
}
