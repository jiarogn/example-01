package org.example.exampleapp.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String blockchainId;
}
