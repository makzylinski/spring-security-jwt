package com.JWTDemo.Jwt_Demo.controller;

import com.JWTDemo.Jwt_Demo.DTO.UserRequest;
import com.JWTDemo.Jwt_Demo.DTO.UserResponse;
import com.JWTDemo.Jwt_Demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.saveUser(request);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
