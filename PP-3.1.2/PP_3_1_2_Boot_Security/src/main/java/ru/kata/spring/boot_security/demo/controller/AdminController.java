package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = (User) userService.loadUserByUsername(username);
        model.addAttribute("username", username);
        model.addAttribute("roles", user.getRole());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user, @RequestParam("roles") List<Long> roleIds) {
        try {
            List<Role> roles = roleService.findByIds(roleIds);
            user.setRole(roles);
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "redirect:/admin";
    }

    @PostMapping("/update_user")
    public String updateUser(@ModelAttribute User user, @RequestParam Long id, @RequestParam("roles") List<Long> roleIds, Model model) {
        user.setId(id);
        try {
            List<Role> roles = roleService.findByIds(roleIds);
            user.setRole(roles);
            userService.updateUser(user, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
