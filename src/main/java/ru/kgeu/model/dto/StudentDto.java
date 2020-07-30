package ru.kgeu.model.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class StudentDto {
    private String lastname;

    private Long id;

    private String firstname;

    private String studentGroup;

    private Map<String, IndebtednessDto> indebtednesses;
}
