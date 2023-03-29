package com.cbc.cbc.userRegistration.service;

import com.cbc.cbc.userRegistration.controller.request.UserConfirmationRequest;
import com.cbc.cbc.userRegistration.controller.request.UserRegistrationRequest;
import com.cbc.cbc.userRegistration.pojo.User;
import com.cbc.cbc.userRegistration.pojo.UserRole;
import com.cbc.cbc.userRegistration.repository.UserRepository;
import com.cbc.cbc.userRegistration.pojo.ConfirmationToken;
import com.cbc.cbc.userRegistration.pojo.ConfirmationTokenGenerator;
import com.cbc.cbc.userRegistration.utils.validator.PhoneValidator;
import com.cbc.cbc.userRegistration.utils.validator.TokenValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("could not find user with phone number %s", phoneNumber)));
    }

    public String registerUser(UserRegistrationRequest request) throws IllegalAccessException {
        validateRequest(request);
        User userToRegister = getUserToRegister(request);
        userRepository.save(userToRegister);

        ConfirmationToken confirmationToken = ConfirmationTokenGenerator.confirmationTokenGenerator(userToRegister);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //todo: send whatsapp msg with the token
        return confirmationToken.getToken();
    }

    private void validateRequest(UserRegistrationRequest request) throws IllegalAccessException {
        if (!PhoneValidator.validatePhoneNumber(request.getPhoneNumber())) {
            throw new IllegalAccessException(String.format("Phone number: %s is not valid", request.getPhoneNumber()));
        }
        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new IllegalStateException(String.format("There is already a userToRegister with phone number %s", request.getPhoneNumber()));
        }
    }

    private User getUserToRegister(UserRegistrationRequest request) {
        User userToRegister = User.builder()
                .phoneNumber(request.getPhoneNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .role(UserRole.USER)
                .build();
        userToRegister.setPassword(bCryptPasswordEncoder.encode(userToRegister.getPassword()));
        return userToRegister;
    }


    public String confirmUser(UserConfirmationRequest confirmationRequest) throws IllegalAccessException {
        String token = confirmationRequest.getToken();

        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token);
        if (TokenValidator.validateConfirmationToken(confirmationToken)) {
            confirmationTokenService.updateConfirmedAt(token);
            enableUser(confirmationToken.getUser().getPhoneNumber());
            return "User confirmed";
        }
        return "Invalid token";
    }

    private void enableUser(String phoneNumber) {
        userRepository.enableUser(phoneNumber);
    }

}
