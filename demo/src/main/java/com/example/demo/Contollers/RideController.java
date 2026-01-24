package com.example.demo.Contollers;


import com.example.demo.DTOs.RideRequest;
import com.example.demo.Entities.Ride;
import com.example.demo.Entities.User;
import com.example.demo.Services.RideService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideService rideService;
    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<Ride> create( @RequestBody RideRequest request){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        Optional<Ride> ride=rideService.createRide(username,request);
        return ride.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ride> delete(@PathVariable String id) {
       Optional<Ride> r=rideService.deleteRide(id);
        return r.map(ride -> new ResponseEntity<>(ride, HttpStatus.NO_CONTENT)).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
