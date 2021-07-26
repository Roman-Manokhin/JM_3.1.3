package ru.rmanokhin.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rmanokhin.crud.model.Role;
import ru.rmanokhin.crud.model.UserSecurity;
import ru.rmanokhin.crud.service.RoleService;
import ru.rmanokhin.crud.service.UserSecurityService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserSecurityService userSecurityService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserSecurityService userSecurityService, RoleService roleService) {
        this.roleService = roleService;
        this.userSecurityService = userSecurityService;
    }

    @GetMapping(value = "/user")
    public String getUserInfo(@AuthenticationPrincipal UserSecurity user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user_info";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(@AuthenticationPrincipal UserSecurity user, Model model) {
        model.addAttribute("userSecurity", user);
        model.addAttribute("roles", user.getRoles());
        model.addAttribute("allUsers", userSecurityService.getAllUsers());
        return "admin_info";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserSecurity());
        model.addAttribute("roles", roleService.getAllRole());
        return "new";
    }

    @GetMapping(value = "/admin/update/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userSecurityService.getUserById(id));
        model.addAttribute("role", roleService.getAllRole());
        return "update";
    }

    @PostMapping(value = "/admin/add")
    private String addUser(@ModelAttribute UserSecurity user,
                           @RequestParam(value = "checkBoxRoles") String[] checkBoxes) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxes) {
            roles.add(roleService.getRoleByLogin(role));
        }
        user.setRoles(roles);
        userSecurityService.addUser(user);
        return "redirect:/admin";
    }

    @PutMapping(value = "/admin/{id}")
    public String updateUser(@ModelAttribute UserSecurity user,
                             @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {

        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByLogin(role));
        }
        user.setRoles(roles);
        userSecurityService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/remove/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userSecurityService.deleteUser(id);
        return "redirect:/admin";
    }
}
