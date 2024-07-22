package com.example.apikovach.service;

import com.example.apikovach.dto.UserDto;
import com.example.apikovach.model.User;
import com.example.apikovach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapping, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::dtoUser)
                .collect(Collectors.toList());
    }

    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }
}
