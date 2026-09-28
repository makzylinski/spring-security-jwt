package com.JWTDemo.Jwt_Demo.repository;

import com.JWTDemo.Jwt_Demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
