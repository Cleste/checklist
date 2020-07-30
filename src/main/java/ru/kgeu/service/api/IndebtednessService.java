package ru.kgeu.service.api;

import javax.servlet.http.HttpServletRequest;

import ru.kgeu.model.dto.IndebtednessDto;
import ru.kgeu.model.dto.StudentIndebtednessDto;
import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.User;

public interface IndebtednessService {

    IndebtednessDto findByStudentAndRole(User userById, Role username);

    void save(Role role, Long studentId, String description);

    void delete(Long id);

    StudentIndebtednessDto getIndebtedness(Long id);

    void notifyStudent(Long id, HttpServletRequest httpServletRequest);
}
