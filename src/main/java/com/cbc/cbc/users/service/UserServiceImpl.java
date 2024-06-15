package com.cbc.cbc.users.service;

import com.cbc.cbc.users.mapper.UserMapper;
import com.cbc.cbc.users.model.dto.UserDTO;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private UserMapper userMapper;

    @Override
    public RegisterUserResponse register(UserDTO userDTO) {
        User savedUser = repository.save(userMapper.toUser(userDTO));
        return userMapper.toRegisterResponse(savedUser);
    }

    @Override
    public UserDTO login(Long userId, String username, String password) {
        User user;
        if (userId != null) {
            user = repository.findById(userId).orElse(null);
        } else {
            user = repository.findByUsername(username);
        }
        if (user != null) {
            boolean passwordMatch = user.getPassword().equals(password);
            boolean usernameMatch = user.getUsername().equals(username);
            if (passwordMatch && usernameMatch) {
                return UserDTO.builder()
                        .id(user.getId())
                        .username(username)
                        .build();
            }
        }
        return null;
    }
}
