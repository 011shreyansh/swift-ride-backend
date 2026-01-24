package com.example.demo.Repos;


import com.example.demo.Entities.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RideRepo extends MongoRepository<Ride,String> {
    public Optional<Ride> findByRideId(String rideId);

}
