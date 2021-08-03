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

    @GetMapping(value = "/")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null);
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserInfo(@AuthenticationPrincipal UserSecurity user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user_info";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(@AuthenticationPrincipal UserSecurity user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        model.addAttribute("allUsers", userSecurityService.getAllUsers());
        return "admin_panel";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(@AuthenticationPrincipal UserSecurity user, Model model) {
        model.addAttribute("user", new UserSecurity());
        model.addAttribute("user", user);
        return "new";
    }

    @PostMapping(value = "/admin/add")
    private String addUser(@ModelAttribute UserSecurity user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userSecurityService.addUser(user);
        return "redirect:/admin";
    }

    @PutMapping(value = "/admin/update")
    public String updateUser(@ModelAttribute UserSecurity user,
                             @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : checkBoxRoles) {
            roles.add(roleService.getRoleByName(role));
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
