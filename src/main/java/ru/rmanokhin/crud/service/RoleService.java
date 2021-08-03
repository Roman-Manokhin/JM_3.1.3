package ru.rmanokhin.crud.service;

import ru.rmanokhin.crud.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
//    List<Role> getAllRole();
//    void updateRole(Role role);
    Role getRoleByName(String login);
}
