package ru.kgeu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kgeu.constant.ViewNames;
import ru.kgeu.model.dto.UserDto;
import ru.kgeu.service.api.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping(ViewNames.User.PAGE_URL)
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('DEAN')")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return ViewNames.User.VIEW_NAME;
    }



}
