package ru.rmanokhin.crud.config.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rmanokhin.crud.model.Role;
import ru.rmanokhin.crud.model.UserSecurity;
import ru.rmanokhin.crud.service.RoleService;
import ru.rmanokhin.crud.service.UserSecurityService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class FirstInitDBByUsers {

    private final UserSecurityService userSecurityService;
    private final RoleService roleService;

    @Autowired
    public FirstInitDBByUsers(UserSecurityService userSecurityService, RoleService roleService) {
        this.userSecurityService = userSecurityService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initDB() {

        Role user = new Role("USER");
        roleService.addRole(user);
        Role admin = new Role("ADMIN");
        roleService.addRole(admin);

        UserSecurity userUser = new UserSecurity("user@mail.ru", "user",
                "user", "user", (byte) 24);
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(user);
        userUser.setRoles(roleUser);

        UserSecurity userUser1 = new UserSecurity("user1@mail.ru", "user",
                "user1", "user1", (byte) 25);
        userUser1.setRoles(roleUser);

        UserSecurity userUser2 = new UserSecurity("user2@mail.ru", "user",
                "user1", "user2", (byte) 35);
        userUser2.setRoles(roleUser);

        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(user);
        roleAdmin.add(admin);
        UserSecurity userAdmin = new UserSecurity("admin@mail.ru", "admin",
                "admin", "admin", (byte) 30);
        userAdmin.setRoles(roleAdmin);

        userSecurityService.addUser(userUser);
        userSecurityService.addUser(userUser1);
        userSecurityService.addUser(userUser2);
        userSecurityService.addUser(userAdmin);

    }
}
