package com.JWTDemo.Jwt_Demo.controller;

import com.JWTDemo.Jwt_Demo.DTO.UserRequest;
import com.JWTDemo.Jwt_Demo.DTO.UserResponse;
import com.JWTDemo.Jwt_Demo.model.User;
import com.JWTDemo.Jwt_Demo.service.JwtService;
import com.JWTDemo.Jwt_Demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.saveUser(request);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        String token = null;
        if(authentication.isAuthenticated()) {
             token = jwtService.generateToken(user.getName());
            System.out.println(token);
        } else {
            System.out.println("Login Failed.");
        }

        return token;
    }
}
