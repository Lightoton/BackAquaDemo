package com.aquaOlsberg.demo.controller;

import com.aquaOlsberg.demo.security.JwtService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @Value("${app.admin.username}")
    private String configuredUsername;

    @Value("${app.admin.password}")
    private String configuredPassword;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (configuredUsername.equals(request.getUsername()) && configuredPassword.equals(request.getPassword())) {
            String token = jwtService.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}

@Data
class LoginRequest {
    private String username;
    private String password;
}

@Data
class AuthResponse {
    private String token;
    public AuthResponse(String token) { this.token = token; }
}