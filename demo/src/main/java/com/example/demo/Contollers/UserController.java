package com.example.demo.Contollers;


import com.example.demo.DTOs.UserRequestDTO;
import com.example.demo.DTOs.UserResponseDTO;
import com.example.demo.Entities.Booking;
import com.example.demo.Entities.User;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequest) {
        User savedUser = userService.save(userRequest.toEntity());

        UserResponseDTO response = new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Booking>> getAllBookings(Authentication auth){
        String client=auth.getName();
        Optional<User> userOpt= userService.getUser(client);
        if(userOpt.isPresent()){
            List<Booking> list=userService.getAllBookings(userOpt.get().getUsername());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }





}
