package ru.kgeu.service.api;

import java.util.List;

import ru.kgeu.model.dto.UserDto;
import ru.kgeu.model.dto.UserRegistrationDto;
import ru.kgeu.model.entity.User;

public interface UserService {
    User findByUsername(String username);

    List<UserDto> findAll();

    void save(UserRegistrationDto user);

    UserDto findAllStudents();
}
