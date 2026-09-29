package com.JWTDemo.Jwt_Demo.DTO;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(@NotBlank String name, @NotBlank String password) {
}
