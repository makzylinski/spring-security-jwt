package com.JWTDemo.Jwt_Demo.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String name) {
        super("User already exists: " + name);
    }
}
