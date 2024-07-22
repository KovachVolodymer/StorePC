package com.example.apikovach.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;


    public User(String name, String email) {
    }
}
