package com.example.apikovach.repository;

import com.example.apikovach.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
