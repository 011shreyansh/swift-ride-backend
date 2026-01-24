package com.example.demo.Entities;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "Ride")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ride {
    @Id
    private String id;
    @NonNull
    private String driverId;
    @NonNull
    @Indexed(unique = true)
    private String rideId;
    @NonNull
    private String origin;
    @NonNull
    private String destination;
    @NonNull
    private LocalDateTime time;
    @NonNull
    private int seats;
    @NonNull
    private Double price;
    @NonNull
    private String status;


}
