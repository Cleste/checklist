package ru.kgeu.controller;

import java.security.Principal;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kgeu.constant.ViewNames;
import ru.kgeu.service.api.IndebtednessService;
import ru.kgeu.service.api.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping(ViewNames.CheckList.PAGE_URL)
@PreAuthorize("hasRole('STUDENT')")
public class ChecklistController {

    private final UserService userService;
    private final IndebtednessService indebtednessService;

    @GetMapping
    public String getChecklist(Principal principal, Model model) {
        model.addAttribute("student", userService.getStudentCheckList(principal.getName()));
        return ViewNames.CheckList.VIEW_NAME;
    }

    @GetMapping("/{id}")
    public String getSingleIndebtedness(Model model, @PathVariable Long id) {
        model.addAttribute("indebtedness", indebtednessService.getIndebtedness(id));
        return ViewNames.CheckList.SINGLE_VIEW_NAME;
    }
}
