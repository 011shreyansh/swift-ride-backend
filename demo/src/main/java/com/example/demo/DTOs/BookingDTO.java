package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDTO {
    private String driverId;
    private String origin;
    private String destination;
    private LocalDateTime date;
    private Double price;
    private String status;
    private String rideId;
}
