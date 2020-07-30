package ru.kgeu.service.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ru.kgeu.model.dto.StudentDto;
import ru.kgeu.model.dto.UserDto;
import ru.kgeu.model.dto.UserRegistrationDto;
import ru.kgeu.model.entity.User;

public interface UserService {
    User findByUsername(String username);

    List<UserDto> findAll();

    void save(UserRegistrationDto user, HttpServletRequest httpRequest);

    List<StudentDto> findAllStudents();

    void activate(String password, String token);

    User findById(Long id);

    StudentDto getStudentCheckList(String username);
}
