package com.JWTDemo.Jwt_Demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank @Size(min = 3, max = 50) String name,
        @NotBlank @Size(min = 8, max = 100) String password) {
}
