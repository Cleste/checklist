package ru.kgeu.controller;

import java.security.Principal;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.kgeu.constant.ViewNames;
import ru.kgeu.model.dto.IndebtednessDto;
import ru.kgeu.model.entity.User;
import ru.kgeu.service.api.IndebtednessService;
import ru.kgeu.service.api.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping(ViewNames.Indebtedness.PAGE_URL)
@PreAuthorize("hasAnyRole('DEAN', 'ACCOUNTANT', 'CPT', 'DORMITORY_WORKER', 'GRADUATING_DEPARTMENT', 'INSTITUTE_WORKER', 'OMP_KB')")
public class IndebtednessController {

    private final IndebtednessService indebtednessService;
    private final UserService userService;

    @GetMapping
    public String getIndebtedness(@RequestParam Long id,
                                  Model model,
                                  Principal principal) {
        User user = userService.findByUsername(principal.getName());
        User student = userService.findById(id);
        IndebtednessDto byId = indebtednessService.findByStudentAndRole(student, user.getRole());
        model.addAttribute("indebtedness", byId);
        model.addAttribute("student", student);
        return ViewNames.Indebtedness.VIEW_NAME;
    }

    @PostMapping
    public String addIndebtedness(@RequestParam(required = false) String description,
                                  @RequestParam Long studentId,
                                  Principal principal) {
        User user = userService.findByUsername(principal.getName());
        indebtednessService.save(user.getRole(), studentId, description);
        return "redirect:" + ViewNames.Indebtedness.PAGE_URL + "?id=" + studentId;
    }

    @PostMapping("/delete")
    public String deleteIndebtedness(@RequestParam Long id,
                                     @RequestParam Long studentId) {
        indebtednessService.delete(id);
        return "redirect:" + ViewNames.Indebtedness.PAGE_URL + "?id=" + studentId;
    }
}
