package com.example.apikovach.service;

import com.example.apikovach.dto.response.MessageResponse;
import com.example.apikovach.model.User;
import com.example.apikovach.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUser() {

        List<User> users = List.of(new User("1", "John", "john@example.com", "password"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUser();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }


    @Test
    void getUser() {
        User user = new User("1", "John", "john@example.com", "password");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUser("1");

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
        verify(userRepository, times(1)).findById("1");
    }


    @Test
    void patchUser() {
        // Ініціалізація існуючого користувача
        User existingUser = new User("1", "John", "john@example.com", "password");

        // Ініціалізація оновленого користувача
        User updatedUser = new User("1", "Johnny", "johnny@example.com", "newpassword");

        // Налаштування мок-об'єктів
        when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));
        when(userRepository.existsByEmail("johnny@example.com")).thenReturn(false);

        // Виклик методу сервісу
        ResponseEntity<Object> response = userService.patchUser(updatedUser, "1");

        // Перевірка статусу відповіді
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Перевірка типу і змісту повідомлення у відповіді
        assertInstanceOf(MessageResponse.class, response.getBody());
        assertEquals("Patch successful", ((MessageResponse) response.getBody()).message());

        // Перевірка викликів мок-об'єктів
        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(1)).existsByEmail("johnny@example.com");
        verify(userRepository, times(1)).save(existingUser);
    }


    @Test
    void deleteUser() {
        User user = new User("1", "John", "john@example.com", "password");

        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        ResponseEntity<Object> response = userService.deleteUser("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(MessageResponse.class, response.getBody());
        assertEquals("User with id 1 deleted", ((MessageResponse) response.getBody()).message());

        verify(userRepository, times(1)).findById("1");
        verify(userRepository, times(1)).deleteById("1");
    }

}