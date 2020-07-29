package ru.kgeu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kgeu.model.dto.UserDto;
import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.User;
import ru.kgeu.model.mapper.UserMapper;
import ru.kgeu.repository.RoleRepository;
import ru.kgeu.repository.UserRepository;
import ru.kgeu.service.api.UserService;

import java.util.ArrayList;
import java.util.List;

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
    public void save(UserDto userDto) {
        User newUser = userMapper.toUser(userDto);
        newUser.setRoles(new ArrayList<>());
        newUser.getRoles().add(roleRepository.findById(1L).orElse(new Role()));
        userRepository.save(newUser);
    }
}
