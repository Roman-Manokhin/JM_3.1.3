package ru.rmanokhin.crud.service;

import ru.rmanokhin.crud.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User addUser(User user);
    User getUserById(long id);
    void deleteUser(long id);
    User updateUser(User user);
    User getUserByEmail(String email);
}
