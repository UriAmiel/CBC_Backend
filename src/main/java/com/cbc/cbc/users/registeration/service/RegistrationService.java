package com.cbc.cbc.users.registeration.service;

import com.cbc.cbc.users.registeration.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.registeration.model.dto.UserDTO;

public interface RegistrationService {

    RegisterUserResponse registerUser(UserDTO userDTO);
}
