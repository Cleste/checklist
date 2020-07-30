package ru.kgeu.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kgeu.constant.ViewNames;
import ru.kgeu.service.api.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping(ViewNames.Students.PAGE_URL)
@PreAuthorize("hasAnyRole('DEAN', 'ACCOUNTANT', 'CPT', 'DORMITORY_WORKER', 'GRADUATING_DEPARTMENT', 'INSTITUTE_WORKER', 'OMP_KB')")
public class StudentsController {

    private final UserService userService;

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", userService.findAllStudents());
        return ViewNames.Students.VIEW_NAME;
    }
}
