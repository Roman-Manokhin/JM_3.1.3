package ru.rmanokhin.crud.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rmanokhin.crud.model.UserSecurity;

public interface UserSecurityDAO extends JpaRepository<UserSecurity, Long> {

    UserSecurity findByEmail(String name);

}
