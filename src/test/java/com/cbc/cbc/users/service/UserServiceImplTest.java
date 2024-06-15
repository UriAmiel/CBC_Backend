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
    private static final Long USER_ID = 1L;
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

        assertEquals(USER_ID, registerUserResponse.getId());
        assertEquals(USERNAME, registerUserResponse.getUsername());
    }

    @Test
    public void testLoginUser_happyFlow_withUserId_expectUsernameAndId_noPassword() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.ofNullable(getUser()));
        UserDTO loginUser = userService.login(USER_ID, USERNAME, PASSWORD);

        assertEquals(USER_ID, loginUser.getId());
        assertEquals(USERNAME, loginUser.getUsername());
        assertNull(loginUser.getPassword());
    }

    @Test
    public void testLoginUser_happyFlow_withoutUserId_expectUsernameAndId_noPassword() {
        when(userRepository.findById(USER_ID)).thenReturn(null);
        when(userRepository.findByUsername(USERNAME)).thenReturn(getUser());
        UserDTO loginUser = userService.login(null, USERNAME, PASSWORD);

        assertEquals(USER_ID, loginUser.getId());
        assertEquals(USERNAME, loginUser.getUsername());
        assertNull(loginUser.getPassword());
    }

    @Test
    public void testLoginUser_unMatchUsername_expectNull() {
        User user = getUser();
        user.setUsername(user.getUsername() + "1024");
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        UserDTO loginUser = userService.login(USER_ID, USERNAME, PASSWORD);

        assertNull(loginUser);
    }

    @Test
    public void testLoginUser_unMatchPassword_expectNull() {
        User user = getUser();
        user.setPassword(user.getPassword() + "1024");
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        UserDTO loginUser = userService.login(USER_ID, USERNAME, PASSWORD);

        assertNull(loginUser);
    }

    @Test
    public void testLoginUser_nullUserId_expectNull() {
        UserDTO loginUser = userService.login(null, USERNAME, PASSWORD);
        assertNull(loginUser);
    }

    @Test
    public void testLoginUser_nullUsername_expectNull() {
        UserDTO loginUser = userService.login(USER_ID, null, PASSWORD);
        assertNull(loginUser);
    }

    @Test
    public void testLoginUser_nullPassword_expectNull() {
        UserDTO loginUser = userService.login(USER_ID, USERNAME, null);
        assertNull(loginUser);
    }

    private static User getUser() {
        return User.builder()
                .id(USER_ID)
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    private static RegisterUserResponse getRegisterUserResponse() {
        return RegisterUserResponse.builder()
                .id(USER_ID)
                .username(USERNAME)
                .build();
    }

    private static UserDTO getUserDTO() {
        return UserDTO.builder()
                .id(USER_ID)
                .username(USERNAME)
                .build();
    }
}