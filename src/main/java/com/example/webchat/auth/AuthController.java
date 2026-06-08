package com.example.webchat.auth;

import com.example.webchat.auth.dtos.AuthResponse;
import com.example.webchat.auth.dtos.LoginRequest;
import com.example.webchat.auth.dtos.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    public ResponseEntity<Void> register(RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        AuthResponse response = authService.login(loginRequest);
        return ResponseEntity.ok().body(response);
    }
}
