package ru.kgeu.model.dto;

import lombok.Getter;
import lombok.Setter;

import ru.kgeu.model.entity.RoleEnum;

@Getter
@Setter
public class UserRegistrationDto {
    private String username;

    private RoleEnum role;

    private String lastname;

    private String firstname;

    private String studentGroup;
}
