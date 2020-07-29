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
}
