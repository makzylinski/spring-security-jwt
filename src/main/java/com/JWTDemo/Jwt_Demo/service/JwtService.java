package com.JWTDemo.Jwt_Demo.service;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static final long EXPIRATION_MS = 1000 * 60 * 30; // 3 min

    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public String generateToken(String name) {
        Date now = new Date();

        return Jwts.builder()
                .subject(name)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + EXPIRATION_MS))
                .signWith(secretKey)
                .compact();
    }

    public String extractUserName(String token) {
        return "";
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return true;
    }
}
