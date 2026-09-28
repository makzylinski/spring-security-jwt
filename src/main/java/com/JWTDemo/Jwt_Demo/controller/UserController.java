package com.JWTDemo.Jwt_Demo.controller;

import com.JWTDemo.Jwt_Demo.model.User;
import com.JWTDemo.Jwt_Demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> register() {

        return new ResponseEntity<>(new User(), HttpStatus.OK);
    }
}
