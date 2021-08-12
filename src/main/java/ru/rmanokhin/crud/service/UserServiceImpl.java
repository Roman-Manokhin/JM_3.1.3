package ru.rmanokhin.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rmanokhin.crud.DAO.UserDAO;
import ru.rmanokhin.crud.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public User addUser(User user) {
        user.setPasswordUser(getPasswordEncoder().encode(user.getPasswordUser()));
        return userDAO.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.findById(id).get();
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        if (!user.getPasswordUser().equals(getUserById(user.getId()).getPasswordUser())) {
            user.setPasswordUser(getPasswordEncoder().encode(user.getPasswordUser()));
        }
        return userDAO.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
