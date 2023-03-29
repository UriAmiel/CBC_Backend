package com.cbc.cbc.userRegistration.pojo;

import com.cbc.cbc.userRegistration.pojo.User;
import com.cbc.cbc.userRegistration.pojo.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConfirmationTokenGenerator {

    public static ConfirmationToken confirmationTokenGenerator(User user) {
        String token = UUID.randomUUID().toString();
        return ConfirmationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
    }
}
