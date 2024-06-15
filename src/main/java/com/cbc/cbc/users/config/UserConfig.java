package com.cbc.cbc.users.config;

import com.cbc.cbc.users.controller.UserController;
import com.cbc.cbc.users.mapper.UserMapper;
import com.cbc.cbc.users.service.UserService;
import com.cbc.cbc.users.service.UserServiceImpl;
import com.cbc.cbc.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserConfig {

    @Bean
    public UserService userService(UserRepository repository, UserMapper userMapper) {
        return new UserServiceImpl(repository, userMapper);
    }

    @Bean
    public UserController userController(UserService userService) {
        return new UserController(userService);
    }
}
