package ru.rmanokhin.crud.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rmanokhin.crud.model.User;

public interface UserSecurityDAO extends JpaRepository<User, Long> {

    User findByEmail(String name);

}
