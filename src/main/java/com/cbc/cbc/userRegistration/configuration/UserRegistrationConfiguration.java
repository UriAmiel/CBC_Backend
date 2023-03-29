package com.cbc.cbc.userRegistration.configuration;

import com.cbc.cbc.userRegistration.utils.validator.PhoneValidator;
import com.cbc.cbc.userRegistration.utils.validator.TokenValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Import(WebSecurityConfig.class)
public class UserRegistrationConfiguration {

    @Bean
    public PhoneValidator phoneValidator() {
        return new PhoneValidator();
    }

    @Bean
    public TokenValidator tokenValidator() {
        return new TokenValidator();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
