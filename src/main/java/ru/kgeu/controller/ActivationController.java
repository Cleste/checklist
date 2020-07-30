package ru.kgeu.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.kgeu.constant.ViewNames;
import ru.kgeu.model.entity.AccountActivationToken;
import ru.kgeu.service.api.AccountActivationTokenService;
import ru.kgeu.service.api.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping(ViewNames.AccountActivation.PAGE_URL)
public class ActivationController {

    private final AccountActivationTokenService accountActivationTokenService;
    private final UserService userService;

    @GetMapping
    public String getActivationPage(Model model,
                                    @RequestParam String token) {
        AccountActivationToken byToken = accountActivationTokenService.findByToken(token);
        if (byToken == null)
            return ViewNames.Main.PAGE_URL;
        model.addAttribute("token", token);
        return ViewNames.AccountActivation.VIEW_NAME;
    }

    @PostMapping
    public String getActivationPage(@RequestParam String password,
                                    @RequestParam String token) {
        userService.activate(password, token);
        return "redirect:" + ViewNames.Login.VIEW_NAME + "?successfullyActivated=true";
    }
}
