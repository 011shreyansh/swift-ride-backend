package com.example.demo.Services;


import com.example.demo.Entities.Booking;
import com.example.demo.Entities.User;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
   private PasswordEncoder passwordEncoder;
    public User save(User user) {
        if(user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getRoles().isEmpty()) {
            user.getRoles().add("USER");
        }
        return userRepo.save(user);
    }


    public Boolean delete(User user){
        Optional<User> u=userRepo.findByUsername(user.getUsername());
        if(u.isPresent()){
            userRepo.delete(u.get());
            return true;
        }
        return false;
    }

    public User updateUser(User user){
        Optional<User> u=userRepo.findByUsername(user.getUsername());
        if(u.isPresent()){
            User updatedUser=u.get();
            updatedUser.setUsername(user.getUsername());
//            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(updatedUser);
            return updatedUser;

        }
        return null;
    }

    public List<Booking> getAllBookings(String username){
        Optional<User> user=userRepo.findByUsername(username);
        if(user.isPresent()){
            return user.get().getBookingList();
        }
        return  Collections.emptyList();
    }

    public Optional<User> getUser(String username){
        return userRepo.findByUsername(username);
    }

}
