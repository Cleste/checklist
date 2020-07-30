package ru.kgeu.service.impl;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.kgeu.constant.Messages;
import ru.kgeu.constant.ViewNames;
import ru.kgeu.model.dto.IndebtednessDto;
import ru.kgeu.model.dto.MailDto;
import ru.kgeu.model.dto.StudentIndebtednessDto;
import ru.kgeu.model.entity.Indebtedness;
import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.User;
import ru.kgeu.model.mapper.IndebtednessMapper;
import ru.kgeu.repository.IndebtednessRepository;
import ru.kgeu.service.api.EmailService;
import ru.kgeu.service.api.IndebtednessService;
import ru.kgeu.service.api.UserService;

@Service
@RequiredArgsConstructor
public class IndebtednessServiceImpl implements IndebtednessService {

    private final IndebtednessRepository indebtednessRepository;
    private final IndebtednessMapper indebtednessMapper;
    private final UserService userService;
    private final EmailService emailService;

    @Override
    public IndebtednessDto findByStudentAndRole(User student, Role role) {
        Indebtedness indebtedness = indebtednessRepository.findByRoleAndStudent(role, student);
        return indebtedness != null ? indebtednessMapper.toIndebtednessDto(indebtedness) : null;
    }

    @Override
    public void save(Role role, Long studentId, String description) {
        User student = userService.findById(studentId);
        Indebtedness indebtedness = indebtednessRepository.findByRoleAndStudent(role, student);
        if (indebtedness != null) {
            indebtedness.setDescription(description);
            indebtednessRepository.save(indebtedness);
            return;
        }
        indebtedness = Indebtedness.builder()
                .role(role)
                .description(description)
                .student(student)
                .build();
        indebtednessRepository.save(indebtedness);
    }

    @Override
    public void delete(Long id) {
        Indebtedness indebtedness = indebtednessRepository.findById(id).get();
        indebtednessRepository.delete(indebtedness);
    }

    @Override
    public StudentIndebtednessDto getIndebtedness(Long id) {
        Indebtedness indebtedness = indebtednessRepository.findById(id).get();
        return StudentIndebtednessDto.builder()
                .description(indebtedness.getDescription())
                .role(indebtedness.getRole().getRusName())
                .build();
    }

    @Override
    public void notifyStudent(Long id, HttpServletRequest request) {
        Indebtedness indebtedness = indebtednessRepository.findById(id).get();
        User user = indebtedness.getStudent();
        Role role = indebtedness.getRole();
        String url = String.format("%s://%s:%s", request.getScheme(), request.getServerName(),
                request.getServerPort()) + ViewNames.CheckList.PAGE_URL;
        emailService.sendMail(MailDto.builder()
                .subject("Уведомление о задолжности")
                .text(String.format(Messages.NOTIFY, user.getFirstname(), user.getLastname(), role.getRusName(), url))
                .to(user.getUsername())
                .build());
    }
}
