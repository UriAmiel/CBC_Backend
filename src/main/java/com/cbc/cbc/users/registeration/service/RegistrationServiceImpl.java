package com.cbc.cbc.users.registeration.service;

import com.cbc.cbc.users.mapper.UserMapper;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.registeration.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.registeration.model.dto.UserDTO;
import com.cbc.cbc.users.registeration.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository repository;
    private UserMapper userMapper;

    @Override
    public RegisterUserResponse registerUser(UserDTO userDTO) {
        User savedUser = repository.save(userMapper.toUser(userDTO));
        return userMapper.toRegisterResponse(savedUser);
    }
}
