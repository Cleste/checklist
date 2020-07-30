package ru.kgeu.service.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.kgeu.model.dto.UserDto;
import ru.kgeu.model.dto.UserRegistrationDto;
import ru.kgeu.model.entity.User;
import ru.kgeu.model.mapper.UserMapper;
import ru.kgeu.repository.RoleRepository;
import ru.kgeu.repository.UserRepository;
import ru.kgeu.service.api.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    @Override
    public void save(UserRegistrationDto userDto) {
        User newUser = User.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .build();
        if (userDto.getRole() == null) {
            newUser.setRoles(new ArrayList<>());
            newUser.getRoles().add(roleRepository.findByName("ROLE_STUDENT"));
            newUser.setStudentGroup(userDto.getStudentGroup());
        } else {
            newUser.setRoles(new ArrayList<>());
            newUser.getRoles().add(roleRepository.findByName(userDto.getRole().toString()));
        }
        userRepository.save(newUser);
    }

    @Override
    public UserDto findAllStudents() {
        return userMapper.toUserDto(userRepository
                .findByRolesContains(roleRepository.findByName("ROLE_STUDENT")));
    }
}
