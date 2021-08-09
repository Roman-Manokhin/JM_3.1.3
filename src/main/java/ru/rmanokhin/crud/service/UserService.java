package ru.rmanokhin.crud.service;

import ru.rmanokhin.crud.model.User;

import java.util.List;

public interface UserService {
    User getUserByLogin(String name);
    void addUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteUser(long id);
    void updateUser(User user);
}
