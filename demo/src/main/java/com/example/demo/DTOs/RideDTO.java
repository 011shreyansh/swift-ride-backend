package com.example.demo.DTOs;


import com.example.demo.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDTO {
     private String rideId;
     private String origin;
     private String destination;
     private LocalDateTime time;
     private int seat;
     private String status;
     private double price;
     private User user;



}
