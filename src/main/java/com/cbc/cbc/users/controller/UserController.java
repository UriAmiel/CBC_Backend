package com.cbc.cbc.users.controller;

import com.cbc.cbc.users.model.dto.UserDTO;
import com.cbc.cbc.users.model.dto.RegisterUserResponse;
import com.cbc.cbc.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public RegisterUserResponse registerUser(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam Long userId, @RequestParam String username, @RequestParam String password) {
        UserDTO returnedUser = userService.login(userId, username, password);
        return returnedUser != null ?
                ResponseEntity.status(HttpStatus.OK).body(returnedUser) :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Could not login user");
    }
}
