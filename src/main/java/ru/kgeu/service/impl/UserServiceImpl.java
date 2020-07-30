package ru.kgeu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kgeu.constant.Messages;
import ru.kgeu.constant.ViewNames;
import ru.kgeu.model.dto.IndebtednessDto;
import ru.kgeu.model.dto.MailDto;
import ru.kgeu.model.dto.StudentDto;
import ru.kgeu.model.dto.UserDto;
import ru.kgeu.model.dto.UserRegistrationDto;
import ru.kgeu.model.entity.AccountActivationToken;
import ru.kgeu.model.entity.Indebtedness;
import ru.kgeu.model.entity.User;
import ru.kgeu.model.mapper.UserMapper;
import ru.kgeu.repository.RoleRepository;
import ru.kgeu.repository.UserRepository;
import ru.kgeu.service.api.AccountActivationTokenService;
import ru.kgeu.service.api.EmailService;
import ru.kgeu.service.api.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final AccountActivationTokenService accountActivationTokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    @Override
    @Transactional
    public void save(UserRegistrationDto userDto, HttpServletRequest request) {
        User newUser = User.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .username(userDto.getUsername())
                .active(false)
                .build();
        if (userDto.getRole() == null) {
            newUser.setRole(roleRepository.findByName("ROLE_STUDENT"));
            newUser.setStudentGroup(userDto.getStudentGroup());
        } else {
            newUser.setRole(roleRepository.findByName(userDto.getRole().toString()));
        }
        userRepository.save(newUser);
        AccountActivationToken token = accountActivationTokenService.generateToken(newUser);
        String url = String.format("%s://%s:%s", request.getScheme(), request.getServerName()
                , request.getServerPort()) + ViewNames.AccountActivation.PAGE_URL + "?token=" + token.getToken();
        emailService.sendMail(MailDto.builder()
                .subject("Активация аккаунта")
                .text(String.format(Messages.ACTIVATION_LINK_SENT, url))
                .to(userDto.getUsername())
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> findAllStudents() {
        List<User> users = userRepository.findByRole(roleRepository.findByName("ROLE_STUDENT"));
        List<StudentDto> students = new ArrayList<>();
        for (User user : users) {
            students.add(parseStudentInfo(user));
        }
        return students;
    }

    @Override
    @Transactional
    public void activate(String password, String token) {
        AccountActivationToken byToken = accountActivationTokenService.findByToken(token);
        User user = byToken.getUser();
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        accountActivationTokenService.delete(byToken);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public StudentDto getStudentCheckList(String username) {
        User user = userRepository.findByRoleAndUsername(roleRepository.findByName("ROLE_STUDENT"), username);
        return parseStudentInfo(user);
    }

    private StudentDto parseStudentInfo(User user) {
        StudentDto student = StudentDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .studentGroup(user.getStudentGroup())
                .build();
        Map<String, IndebtednessDto> indebtednesses = new HashMap<>();
        for (Indebtedness indebtedness : user.getIndebtednesses()) {
            indebtednesses.put(indebtedness.getRole().getRusName(), IndebtednessDto.builder()
                    .description(indebtedness.getDescription())
                    .id(indebtedness.getId())
                    .build());
        }
        student.setIndebtednesses(indebtednesses);
        return student;
    }
}
