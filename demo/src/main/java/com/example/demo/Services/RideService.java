package com.example.demo.Services;



import com.example.demo.DTOs.RideRequest;
import com.example.demo.Entities.Ride;
import com.example.demo.Entities.User;
import com.example.demo.Repos.RideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RideService {
    @Autowired
    private RideRepo rideRepo;
    @Autowired
    private UserService userService;

    @Transactional
    public Optional<Ride> createRide(String username, RideRequest request) {
        Optional<User> user = userService.getUser(username);
        if (user.isPresent()) {
            User u = user.get();
            if (u.getRoles().contains("DRIVER")) {
                Ride ride = new Ride();
                ride.setDriverId(u.getId());
                ride.setOrigin(request.getOrigin());
                ride.setDestination(request.getDestination());
                ride.setSeats(request.getSeats());
                ride.setPrice(request.getPrice());
                ride.setRideId(String.valueOf(Math.random()));
                ride.setTime(request.getTime());
                return Optional.of(rideRepo.save(ride));
            }

        }
        return Optional.empty();

    }
    public Optional<Ride> deleteRide(String id) {
        Optional<Ride> ride = rideRepo.findById(id);
         ride.ifPresent(r->rideRepo.delete(r));
         return ride;
    }
    public Optional<Ride> save(Ride ride){
         return Optional.of(rideRepo.save(ride));
    }
}
