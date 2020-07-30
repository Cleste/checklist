package ru.kgeu.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kgeu.constant.ViewNames;
import ru.kgeu.model.dto.UserRegistrationDto;
import ru.kgeu.service.api.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping(ViewNames.User.PAGE_URL)
@PreAuthorize("hasRole('DEAN')")
public class UserController {

    private final UserService userService;

    @ModelAttribute("newUser")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }


    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return ViewNames.User.VIEW_NAME;
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("newUser") UserRegistrationDto userDto) {
        userService.save(userDto);
        return "redirect:" + ViewNames.User.VIEW_NAME;
    }
}
