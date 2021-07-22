package ru.rmanokhin.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rmanokhin.crud.model.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserSecurityService userSecurityService;

    @Autowired
    public UserDetailsServiceImpl(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserSecurity user = userSecurityService.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", login));
        }
        return user;
    }
}
