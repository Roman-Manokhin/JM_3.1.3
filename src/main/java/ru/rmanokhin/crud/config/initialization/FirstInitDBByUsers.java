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
    public void initDB(){

        Role user = new Role("ROLE_USER");
        roleService.addRole(user);
        Role admin = new Role("ROLE_ADMIN");
        roleService.addRole(admin);

        UserSecurity userUser = new UserSecurity("USER", "USER", "user", "user", "user@mail.ru");
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(user);
        userUser.setRoles(roleUser);

        UserSecurity userUser1 = new UserSecurity("USER1", "USER1", "user1", "user1", "user1@mail.ru");
        userUser1.setRoles(roleUser);

        UserSecurity userUser2 = new UserSecurity("USER2", "USER2", "user1", "user2", "user2@mail.ru");
        userUser2.setRoles(roleUser);

        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(user);
        roleAdmin.add(admin);
        UserSecurity userAdmin = new UserSecurity("ADMIN", "ADMIN", "admin", "admin", "admin@mail.ru");
        userAdmin.setRoles(roleAdmin);

        userSecurityService.addUser(userUser);
        userSecurityService.addUser(userUser1);
        userSecurityService.addUser(userUser2);
        userSecurityService.addUser(userAdmin);

    }
}
