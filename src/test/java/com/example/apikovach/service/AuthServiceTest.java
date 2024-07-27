package com.example.apikovach.service;

import com.example.apikovach.dto.request.RegisterRequest;
import com.example.apikovach.dto.response.MessageResponse;
import com.example.apikovach.model.User;
import com.example.apikovach.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    public AuthService authService;

    @Mock
    public UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void register_Success() {
        RegisterRequest request = new RegisterRequest(
                "Vova",
                "kovach@gmail.com",
                "123456"
        );

        User user = new User(
                request.name(),
                request.email(),
                request.password()
        );

        when(userRepository.existsByEmail(request.email())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        ResponseEntity<Object> response = authService.register(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("register successful", ((MessageResponse) Objects.requireNonNull(response.getBody())).message());

        verify(userRepository, times(1)).save(any(User.class));
        verify(userRepository, times(1)).existsByEmail(request.email());
    }
    @Test
    void register_EmailExists() {
        RegisterRequest request = new RegisterRequest(
                "Vova",
                "kovach@gmail.com",
                "123456"
        );

        when(userRepository.existsByEmail(request.email())).thenReturn(true);

        ResponseEntity<Object> response = authService.register(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email exists", ((MessageResponse) Objects.requireNonNull(response.getBody())).message());

        verify(userRepository, times(0)).save(any(User.class));
        verify(userRepository, times(1)).existsByEmail(request.email());
    }

}