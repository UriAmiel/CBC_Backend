package com.cbc.cbc.userRegistration.controller;

import com.cbc.cbc.userRegistration.constants.RegistrationConstants;
import com.cbc.cbc.userRegistration.controller.request.UserConfirmationRequest;
import com.cbc.cbc.userRegistration.controller.request.UserRegistrationRequest;
import com.cbc.cbc.userRegistration.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RegistrationConstants.CONTROLLER_PATH)
@CrossOrigin
public class RegistrationController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping(RegistrationConstants.REGISTRATION_PATH)
    public String registerUser(@RequestBody UserRegistrationRequest request) throws IllegalAccessException {
        return userRegistrationService.registerUser(request);
    }

    @PostMapping(RegistrationConstants.CONFIRMATION_PATH)
    public String confirmUser(@RequestBody UserConfirmationRequest userConfirmationRequest) throws IllegalAccessException {
        return userRegistrationService.confirmUser(userConfirmationRequest);
    }

}
