package ru.kgeu.security.auth;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.RoleEnum;
import ru.kgeu.model.entity.User;
import ru.kgeu.repository.RoleRepository;
import ru.kgeu.service.api.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return UserDetailsFactory.create(user);
    }
}
