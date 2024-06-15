package com.cbc.cbc.users.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class RegisterUserResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
}
