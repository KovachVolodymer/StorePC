package com.example.apikovach.service;

import com.example.apikovach.dto.UserDto;
import com.example.apikovach.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMapper {

    public UserDto dtoUser(User user, String response) {
        return new UserDto(
                user.getName(),
                user.getEmail(),
                response
        );
    }


}
