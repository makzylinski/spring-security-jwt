package com.JWTDemo.Jwt_Demo.controller;

import com.JWTDemo.Jwt_Demo.DTO.LoginResponse;
import com.JWTDemo.Jwt_Demo.DTO.UserLoginRequest;
import com.JWTDemo.Jwt_Demo.DTO.UserRequest;
import com.JWTDemo.Jwt_Demo.DTO.UserResponse;
import com.JWTDemo.Jwt_Demo.service.JwtService;
import com.JWTDemo.Jwt_Demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        // Throws AuthenticationException on bad credentials -> GlobalExceptionHandler returns 401
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.name(), request.password()));

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
