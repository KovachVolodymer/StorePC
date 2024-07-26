package com.example.apikovach.service;

import com.example.apikovach.dto.request.LoginRequest;
import com.example.apikovach.dto.request.RegisterRequest;
import com.example.apikovach.dto.response.MessageResponse;
import com.example.apikovach.model.User;
import com.example.apikovach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public ResponseEntity<Object> register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email exists"));
        }

        User user = new User(
                request.name(),
                request.email(),
                request.password()
        );
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("register successful"));
    }




}
