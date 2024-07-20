package com.example.apikovach.service;

import com.example.apikovach.model.User;
import com.example.apikovach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser()
    {
       return userRepository.findAll();
    }

    public Optional<User> getUser(String id)
    {
        return userRepository.findById(id);
    }
}
