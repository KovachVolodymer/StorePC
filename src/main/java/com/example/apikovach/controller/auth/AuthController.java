package com.example.apikovach.controller.auth;

import com.example.apikovach.dto.request.RegisterRequest;
import com.example.apikovach.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    public final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }



}
