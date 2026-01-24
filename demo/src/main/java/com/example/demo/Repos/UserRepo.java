package com.example.demo.Repos;


import com.example.demo.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {
        public Optional<User> findByUsername(String username);
}
