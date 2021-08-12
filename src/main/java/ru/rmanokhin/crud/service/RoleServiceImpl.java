package ru.rmanokhin.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rmanokhin.crud.DAO.RoleDAO;
import ru.rmanokhin.crud.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAllRole() {
        return roleDAO.findAll();
    }

    @Override
    public void addRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void deleteRole(long id) {
        roleDAO.deleteById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.findByName(name);
    }

}
