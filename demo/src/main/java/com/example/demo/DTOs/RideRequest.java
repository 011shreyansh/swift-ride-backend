package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequest {
    private String origin;
    private String destination;
    private LocalDateTime time;
    private int seats;
    private double price;


}
