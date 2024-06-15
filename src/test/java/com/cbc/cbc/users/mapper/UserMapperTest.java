package com.cbc.cbc.users.mapper;

import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.model.dto.UserDTO;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private static final Long ID = 1L;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "054-1111111";
    private UserMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new UserMapperImpl();
    }

    @Test
    public void testToRegisterResponse() {
        User user = User.builder()
                .id(ID)
                .username(USERNAME)
                .password(PASSWORD)
                .phoneNumber(PHONE_NUMBER)
                .communities(Sets.newHashSet())
                .build();

        RegisterUserResponse registerResponse = mapper.toRegisterResponse(user);

        assertEquals(ID, registerResponse.getId());
        assertEquals(USERNAME, registerResponse.getUsername());
    }

    @Test
    public void testToUser() {
        UserDTO userDTO = UserDTO.builder()
                .id(ID)
                .username(USERNAME)
                .password(PASSWORD)
                .phoneNumber(PHONE_NUMBER)
                .build();

        User user = mapper.toUser(userDTO);

        assertEquals(ID, user.getId());
        assertEquals(USERNAME, user.getUsername());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(PHONE_NUMBER, user.getPhoneNumber());
    }
}