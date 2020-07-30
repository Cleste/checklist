package ru.kgeu.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.kgeu.model.dto.UserDto;
import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.User;

@Mapper
public interface UserMapper {
    User toUser(UserDto userDto);

    @Mapping(source = "role", target = "roleName")
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);

    default String mapRoleName(Role role) {
        return role.getRusName();
    }
}
