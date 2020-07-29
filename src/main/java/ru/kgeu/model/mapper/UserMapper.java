package ru.kgeu.model.mapper;

import org.mapstruct.Mapper;
import ru.kgeu.model.dto.UserDto;
import ru.kgeu.model.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
    List<UserDto> toUserDtoList(List<User> userList);
}
