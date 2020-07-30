package ru.kgeu.controller;


import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kgeu.constant.ViewNames;
import ru.kgeu.service.api.IndebtednessService;

@Controller
@PreAuthorize("hasAnyRole('DEAN', 'ACCOUNTANT', 'CPT', 'DORMITORY_WORKER', 'GRADUATING_DEPARTMENT', 'INSTITUTE_WORKER', 'OMP_KB')")
@RequestMapping(ViewNames.Notify.PAGE_URL)
@RequiredArgsConstructor
public class NotifyController {

    private final IndebtednessService indebtednessService;

    @GetMapping("/{id}")
    public String notifyStudent(@PathVariable Long id, HttpServletRequest httpServletRequest){
        indebtednessService.notifyStudent(id, httpServletRequest);
        return "redirect:" + ViewNames.Students.PAGE_URL;
    }
}
