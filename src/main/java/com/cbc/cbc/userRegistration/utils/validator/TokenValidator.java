package com.cbc.cbc.userRegistration.utils.validator;

import com.cbc.cbc.userRegistration.pojo.ConfirmationToken;

import java.time.LocalDateTime;

public class TokenValidator {

    public static boolean validateConfirmationToken(ConfirmationToken confirmationToken) throws IllegalAccessException {
        if (confirmationToken != null) {
            if (confirmationToken.getConfirmedAt() != null) {
                throw new IllegalAccessException("User already confirmed");
            }
            if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
                throw new IllegalAccessException("Token expired");
            }
            return true;
        }
        return false;
    }
}
