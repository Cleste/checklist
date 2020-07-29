package ru.kgeu.service.api;

import ru.kgeu.model.dto.UserDto;
import ru.kgeu.model.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<UserDto> findAll();

    void save(UserDto user);
}
