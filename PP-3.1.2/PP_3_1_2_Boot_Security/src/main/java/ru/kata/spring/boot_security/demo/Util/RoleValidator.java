package ru.kata.spring.boot_security.demo.Util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

@Component
public class RoleValidator {
    public void addRole(User user, String selectedRole) {
        if (!validate(user, selectedRole)) {
            user.getRole().add(new Role(selectedRole));
        }
    }

    private boolean validate(User user, String checkingRole) {
        return user.getRole().stream()
                .map(Role::getRole)
                .anyMatch(roleName -> roleName.equalsIgnoreCase(checkingRole));
    }

}
