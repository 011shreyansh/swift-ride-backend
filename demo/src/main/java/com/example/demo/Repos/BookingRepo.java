package com.example.demo.Repos;


import com.example.demo.Entities.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepo extends MongoRepository<Booking,String> {
}
