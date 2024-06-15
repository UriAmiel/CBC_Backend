package com.cbc.cbc.users.service;

import com.cbc.cbc.users.mapper.UserMapper;
import com.cbc.cbc.users.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.model.dto.UserDTO;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private static final UserDTO userDTO = getUserDTO();
    private static final Long ID = 1L;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);

        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    public void testRegisterUser() {
        User user = getUser();
        RegisterUserResponse response = getRegisterUserResponse();
        Mockito.when(userMapper.toUser(userDTO)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userMapper.toRegisterResponse(user)).thenReturn(response);

        RegisterUserResponse registerUserResponse = userService.register(userDTO);

        assertEquals(ID, registerUserResponse.getId());
        assertEquals(USERNAME, registerUserResponse.getUsername());
    }

    @Test
    public void testLoginUser() {
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(getUser()));
        UserDTO loginUser = userService.login(ID, USERNAME, PASSWORD);

        assertEquals(ID, loginUser.getId());
        assertEquals(USERNAME, loginUser.getUsername());
        assertNull(loginUser.getPassword());
    }

    private static User getUser() {
        return User.builder()
                .id(ID)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    private static RegisterUserResponse getRegisterUserResponse() {
        return RegisterUserResponse.builder()
                .id(ID)
                .username(USERNAME)
                .build();
    }

    private static UserDTO getUserDTO() {
        return UserDTO.builder()
                .id(ID)
                .username(USERNAME)
                .build();
    }
}