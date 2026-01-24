package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;

    public UserResponseDTO(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
