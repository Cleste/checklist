package ru.kgeu.model.mapper;

import org.mapstruct.Mapper;

import ru.kgeu.model.dto.IndebtednessDto;
import ru.kgeu.model.entity.Indebtedness;

@Mapper
public interface IndebtednessMapper {
    Indebtedness toIndebtedness(IndebtednessDto indebtednessDto);
    IndebtednessDto toIndebtednessDto(Indebtedness indebtedness);
}
