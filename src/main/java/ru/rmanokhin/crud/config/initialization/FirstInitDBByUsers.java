package ru.rmanokhin.crud.config.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rmanokhin.crud.model.Role;
import ru.rmanokhin.crud.model.User;
import ru.rmanokhin.crud.service.RoleService;
import ru.rmanokhin.crud.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class FirstInitDBByUsers {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public FirstInitDBByUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initDB() {

        Role user = new Role("USER");
        roleService.addRole(user);
        Role admin = new Role("ADMIN");
        roleService.addRole(admin);

        User userUser = new User("user@mail.ru", "user",
                "user", "user", (byte) 24);
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(user);
        userUser.setRoles(roleUser);

        User userUser1 = new User("user1@mail.ru", "user",
                "user1", "user1", (byte) 25);
        userUser1.setRoles(roleUser);

        User userUser2 = new User("user2@mail.ru", "user",
                "user1", "user2", (byte) 35);
        userUser2.setRoles(roleUser);

        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(user);
        roleAdmin.add(admin);
        User userAdmin = new User("admin@mail.ru", "admin",
                "admin", "admin", (byte) 30);
        userAdmin.setRoles(roleAdmin);

        userService.addUser(userUser);
        userService.addUser(userUser1);
        userService.addUser(userUser2);
        userService.addUser(userAdmin);

    }
}
