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

    @Mapping(source = "roles", target = "roleName")
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);

    default String mapRoleName(List<Role> roles) {
        return roles.get(0).getRusName();
    }
}
