package com.example.apikovach.dto.request;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}
