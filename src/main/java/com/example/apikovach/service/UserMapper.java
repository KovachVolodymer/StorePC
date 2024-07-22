package com.example.apikovach.service;

import com.example.apikovach.dto.UserDto;
import com.example.apikovach.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto dtoUser(User user)
    {
       return new UserDto(
                user.getName(),
               user.getEmail()
        );
    }

}
