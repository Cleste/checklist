package ru.kgeu.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class UserDetailsFactory {

    public static UserDetailsImpl create(User user) {

        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(mapToGrantedAuthority(user.getRoles()))
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(
            Collection<Role> roleForUsers) {

        return roleForUsers.stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).
                collect(Collectors.toList());
    }
}
