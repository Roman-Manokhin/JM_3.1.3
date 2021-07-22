package ru.rmanokhin.crud.service;

import ru.rmanokhin.crud.model.UserSecurity;

import java.util.List;

public interface UserSecurityService {
    UserSecurity getUserByLogin(String name);
    void addUser(UserSecurity userSecurity);
    List<UserSecurity> getAllUsers();
    UserSecurity getUserById(long id);
    void deleteUser(long id);
    void updateUser(UserSecurity userSecurity);
}
