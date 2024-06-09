package com.cbc.cbc.users.registeration.service;

import com.cbc.cbc.users.mapper.UserMapper;
import com.cbc.cbc.users.record.User;
import com.cbc.cbc.users.registeration.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.registeration.model.dto.UserDTO;
import com.cbc.cbc.users.registeration.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class RegistrationServiceImplTest {

    private static final UserDTO userDTO = getUserDTO();
    private static final Long ID = 1L;
    private static final String USERNAME = "username";
    private RegistrationService registrationService;

    @BeforeEach
    public void setUp() {
        RegistrationRepository repository = mock(RegistrationRepository.class);
        UserMapper mapper = mock(UserMapper.class);
        User user = User.builder()
                .id(ID)
                .username(USERNAME)
                .build();

        RegisterUserResponse response = RegisterUserResponse.builder()
                .id(ID)
                .username(USERNAME)
                .build();
        Mockito.when(mapper.toUser(userDTO)).thenReturn(user);
        Mockito.when(repository.save(user)).thenReturn(user);
        Mockito.when(mapper.toRegisterResponse(user)).thenReturn(response);

        registrationService = new RegistrationServiceImpl(repository, mapper);
    }

    @Test
    public void testRegisterUser() {
        RegisterUserResponse registerUserResponse = registrationService.registerUser(userDTO);
        assertEquals(ID, registerUserResponse.getId());
        assertEquals(USERNAME, registerUserResponse.getUsername());
    }

    private static UserDTO getUserDTO() {
        return UserDTO.builder()
                .id(ID)
                .username(USERNAME)
                .build();
    }
}