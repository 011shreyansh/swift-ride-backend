package com.example.demo.Contollers;

import com.example.demo.DTOs.RideIdRequest;
import com.example.demo.Entities.Booking;
import com.example.demo.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = " http://localhost:5173")
@RequestMapping("/booking")
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody RideIdRequest rideIdRequest) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Booking booking = bookingService.createBooking(rideIdRequest.getRideId(), username);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable String id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable String id) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean cancelled = bookingService.cancelBooking(id);
        if (cancelled) {
            return ResponseEntity.ok("Booking cancelled successfully by " + username);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<Booking>> getMyBookings() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Booking> bookings = bookingService.getBookingsForUser(username);
        return ResponseEntity.ok(bookings);
    }


}
