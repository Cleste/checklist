package ru.kgeu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kgeu.constant.ViewNames;

@Controller
@RequiredArgsConstructor
@RequestMapping(ViewNames.Main.PAGE_URL)
public class MainController {

    @GetMapping
    public String getMainPage(){
        return ViewNames.Main.VIEW_NAME;
    }
}
