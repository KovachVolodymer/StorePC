package com.example.apikovach.service;

import com.example.apikovach.dto.response.MessageResponse;
import com.example.apikovach.model.User;
import com.example.apikovach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id) {
       return userRepository.findById(id);
    }

    public ResponseEntity<Object> patchUser(User userData, String id){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User with ID " + id + " not found"));

        Optional.ofNullable(userData.getName()).ifPresent(existingUser::setName);
        Optional.ofNullable(userData.getPassword()).ifPresent(existingUser::setPassword);
        if (userRepository.existsByEmail(userData.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("Email already in use"));
        }
        Optional.ofNullable(userData.getEmail()).ifPresent(existingUser::setEmail);

        userRepository.save(existingUser);
        return  ResponseEntity.ok().body(new MessageResponse("Patch successful"));
    }

    public  ResponseEntity<Object> deleteUser(String id)
    {
        if(userRepository.findById(id).isEmpty())
        {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User with id "+id+" not found"));
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().body(new MessageResponse("User with id "+id+" deleted"));
    }


}
