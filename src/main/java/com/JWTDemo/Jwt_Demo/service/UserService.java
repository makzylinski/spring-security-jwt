package com.JWTDemo.Jwt_Demo.service;

import com.JWTDemo.Jwt_Demo.DTO.UserRequest;
import com.JWTDemo.Jwt_Demo.DTO.UserResponse;
import com.JWTDemo.Jwt_Demo.exception.UserAlreadyExistsException;
import com.JWTDemo.Jwt_Demo.model.User;
import com.JWTDemo.Jwt_Demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repo;
    @Autowired
    PasswordEncoder passwordEncoder;
    public UserResponse saveUser(UserRequest request) {
        if (repo.existsByName(request.name())) {
            throw new UserAlreadyExistsException(request.name());
        }

        User user = new User();
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("ROLE_USER");

        User savedUser = repo.save(user);

        UserResponse userResponse = new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getRole());

        return userResponse;
    }
}
