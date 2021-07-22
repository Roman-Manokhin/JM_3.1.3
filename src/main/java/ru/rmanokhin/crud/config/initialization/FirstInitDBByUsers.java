package ru.rmanokhin.crud.config.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rmanokhin.crud.model.Role;
import ru.rmanokhin.crud.model.UserInfo;
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
        UserInfo userInfoUser = new UserInfo("user", "user", "user@mail.ru");
        UserInfo userInfoUser1 = new UserInfo("user1", "user1", "user1@mail.ru");
        UserInfo userInfoUser2 = new UserInfo("user2", "user2", "user2@mail.ru");
        UserInfo userInfoAdmin = new UserInfo("admin", "admin", "admin@mail.ru");


        UserSecurity userUser = new UserSecurity("USER", "USER");
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(user);
        userUser.setRoles(roleUser);
        userUser.setUserInfo(userInfoUser);

        UserSecurity userUser1 = new UserSecurity("USER1", "USER1");
        userUser1.setRoles(roleUser);
        userUser1.setUserInfo(userInfoUser1);

        UserSecurity userUser2 = new UserSecurity("USER2", "USER2");
        userUser2.setRoles(roleUser);
        userUser2.setUserInfo(userInfoUser2);

        Set<Role> roleAdmin = new HashSet<>();
        roleAdmin.add(user);
        roleAdmin.add(admin);
        UserSecurity userAdmin = new UserSecurity("ADMIN", "ADMIN");
        userAdmin.setRoles(roleAdmin);
        userAdmin.setUserInfo(userInfoAdmin);

        userSecurityService.addUser(userUser);
        userSecurityService.addUser(userUser1);
        userSecurityService.addUser(userUser2);
        userSecurityService.addUser(userAdmin);

    }
}
