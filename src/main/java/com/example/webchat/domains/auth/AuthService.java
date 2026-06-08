package com.example.webchat.domains.auth;

import com.example.webchat.domains.auth.dtos.AuthResponse;
import com.example.webchat.domains.auth.dtos.LoginRequest;
import com.example.webchat.domains.auth.dtos.RegisterRequest;
import com.example.webchat.domains.user.models.User;
import com.example.webchat.domains.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void register(RegisterRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            return;
        }
        User newUser = User.builder()
                .displayName(request.getDisplayName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        userRepository.save(newUser);
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }
        User user = optionalUser.get();
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        AuthResponse response = new AuthResponse();
        response.setAccessToken("dummy_token");
        response.setExpiresIn(6767L);
        return response;
    }
}
