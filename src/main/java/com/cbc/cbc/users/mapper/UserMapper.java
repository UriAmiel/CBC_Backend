package com.cbc.cbc.users.mapper;

import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.registeration.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.registeration.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDTO);

    RegisterUserResponse toRegisterResponse(User user);
}
