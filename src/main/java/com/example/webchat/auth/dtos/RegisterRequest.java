package com.example.webchat.auth.dtos;

import lombok.Data;

@Data
public class RegisterRequest {
    private String displayName;
    private String password;
    private String email;
}
