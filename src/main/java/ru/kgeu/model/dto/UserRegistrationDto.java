package ru.kgeu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ru.kgeu.model.entity.RoleEnum;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserRegistrationDto {
    private String username;

    private RoleEnum role;

    private String lastname;

    private String firstname;

    private String studentGroup;
}
