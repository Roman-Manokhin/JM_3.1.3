package ru.rmanokhin.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rmanokhin.crud.DAO.UserSecurityDAO;
import ru.rmanokhin.crud.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserSecurityDAO userSecurityDAO;

    @Autowired
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserServiceImpl(UserSecurityDAO userSecurityDAO) {
        this.userSecurityDAO = userSecurityDAO;
    }

    @Override
    public User getUserByLogin(String name) {
        return userSecurityDAO.findByEmail(name);
    }

    @Override
    public void addUser(User user) {
        user.setUserPassword(getPasswordEncoder().encode(user.getUserPassword()));
        userSecurityDAO.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userSecurityDAO.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userSecurityDAO.getById(id);
    }

    @Override
    public void deleteUser(long id) {
        userSecurityDAO.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        if (!user.getUserPassword().equals(getUserById(user.getId()).getUserPassword())) {
            user.setUserPassword(getPasswordEncoder().encode(user.getUserPassword()));
        }
        userSecurityDAO.save(user);
    }

}
