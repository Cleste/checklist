package ru.kgeu.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.User;

public final class UserDetailsFactory {

    public static UserDetailsImpl create(User user) {

        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(mapToGrantedAuthority(user.getRole()))
                .active(user.getActive())
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(
            Role roleForUsers) {

        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(roleForUsers.getName()));
        return roles;
    }
}
