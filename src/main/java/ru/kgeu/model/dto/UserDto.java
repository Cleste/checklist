package ru.kgeu.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserDto {
    private Long id;

    private String username;

    private String password;

    private String roleName;

    private String lastname;

    private String firstname;

    private String studentGroup;
}
