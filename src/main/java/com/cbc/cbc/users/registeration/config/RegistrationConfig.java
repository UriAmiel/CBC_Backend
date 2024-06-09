package com.cbc.cbc.users.registeration.config;

import com.cbc.cbc.users.mapper.UserMapper;
import com.cbc.cbc.users.registeration.controller.RegistrationController;
import com.cbc.cbc.users.registeration.repository.RegistrationRepository;
import com.cbc.cbc.users.registeration.service.RegistrationService;
import com.cbc.cbc.users.registeration.service.RegistrationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationConfig {

    @Bean
    public RegistrationService registrationService(RegistrationRepository repository, UserMapper userMapper) {
        return new RegistrationServiceImpl(repository, userMapper);
    }

    @Bean
    public RegistrationController registrationController(RegistrationService registrationService) {
        return new RegistrationController(registrationService);
    }
}
