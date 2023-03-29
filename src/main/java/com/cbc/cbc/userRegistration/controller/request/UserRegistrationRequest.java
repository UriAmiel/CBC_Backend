package com.cbc.cbc.userRegistration.controller.request;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserRegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String password;

}
