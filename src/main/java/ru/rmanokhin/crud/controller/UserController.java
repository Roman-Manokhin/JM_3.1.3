package ru.rmanokhin.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rmanokhin.crud.model.Role;
import ru.rmanokhin.crud.model.User;
import ru.rmanokhin.crud.service.RoleService;
import ru.rmanokhin.crud.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null);
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUserInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "user_info";
    }


    @GetMapping(value = "/admin")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRole());
        return "admin-page";
    }

}
