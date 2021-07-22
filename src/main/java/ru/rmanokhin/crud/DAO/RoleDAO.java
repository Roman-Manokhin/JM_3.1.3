package ru.rmanokhin.crud.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rmanokhin.crud.model.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name = :login")
    Role findRoleByLogin(@Param("login") String name);

}
