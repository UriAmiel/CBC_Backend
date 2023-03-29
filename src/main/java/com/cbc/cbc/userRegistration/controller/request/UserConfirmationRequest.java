package com.cbc.cbc.userRegistration.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class UserConfirmationRequest {

    private final String token;

    public UserConfirmationRequest(@JsonProperty("token")
                                   String token) {
        this.token = token;
    }
}
