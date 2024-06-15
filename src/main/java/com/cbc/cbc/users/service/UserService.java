package com.cbc.cbc.users.service;

import com.cbc.cbc.users.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.model.dto.UserDTO;

public interface UserService {

    RegisterUserResponse register(UserDTO userDTO);

    UserDTO login(Long userId, String username, String password);
}
