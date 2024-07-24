package com.example.apikovach.controller;

import com.example.apikovach.dto.response.MessageResponse;
import com.example.apikovach.model.User;
import com.example.apikovach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser()
    {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable String id)
    {
        return userService.getUser(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchUser(@RequestBody User user, @PathVariable String id)
    {
       return userService.patchUser(user,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id)
    {
        return userService.deleteUser(id);
    }

}
