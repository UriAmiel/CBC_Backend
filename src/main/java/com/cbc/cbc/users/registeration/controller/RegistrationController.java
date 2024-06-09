package com.cbc.cbc.users.registeration.controller;

import com.cbc.cbc.users.registeration.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.registeration.model.dto.UserDTO;
import com.cbc.cbc.users.registeration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/register")
    public RegisterUserResponse registerUser(@RequestBody UserDTO userDTO) {
        return registrationService.registerUser(userDTO);
    }
}
