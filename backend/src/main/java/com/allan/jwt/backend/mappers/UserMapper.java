package com.allan.jwt.backend.mappers;

import com.allan.jwt.backend.dto.SignUpDto;
import com.allan.jwt.backend.dto.UserDTO;
import com.allan.jwt.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}
