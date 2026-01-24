package com.example.demo.Services;

import com.example.demo.Entities.Booking;
import com.example.demo.Entities.Ride;
import com.example.demo.Entities.User;
import com.example.demo.Repos.BookingRepo;
import com.example.demo.Repos.RideRepo;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private RideRepo rideRepo;

    @Autowired
    private UserRepo userRepo;

    public Booking createBooking(String rideId, String passengerUsername) {

        Optional<Ride> rideOpt = rideRepo.findByRideId(rideId);
        Optional<User> passengerOpt = userRepo.findByUsername(passengerUsername);

        if (rideOpt.isPresent() && passengerOpt.isPresent()) {
            Ride ride = rideOpt.get();
            User passenger = passengerOpt.get();

            // Check seat availability
            if (ride.getSeats() <= 0) {
                throw new RuntimeException("No seats available for this ride");
            }

            // Create booking
            Booking booking = new Booking();
            booking.setRideId(ride.getRideId());
            booking.setPassengerId(passenger.getId());
            booking.setStatus("CONFIRMED");
            booking.setBookingtime(LocalDateTime.now());

            // Save booking
            Booking savedBooking = bookingRepo.save(booking);

            // Update passenger’s booking list
            passenger.getBookingList().add(savedBooking);
            userRepo.save(passenger);

            // Decrease available seats
            ride.setSeats(ride.getSeats() - 1);
            rideRepo.save(ride);

            return savedBooking;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ride or passenger not found");
    }


    public Optional<Booking> getBookingById(String id) {
        return bookingRepo.findById(id);
    }

    public boolean cancelBooking(String bookingId) {
        Optional<Booking> bookingOpt = bookingRepo.findById(bookingId);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();

            // Update ride seats back
            Optional<Ride> rideOpt = rideRepo.findById(booking.getRideId());
            rideOpt.ifPresent(ride -> {
                ride.setSeats(ride.getSeats() + 1);
                rideRepo.save(ride);
            });

            // Update passenger’s booking list
            Optional<User> passengerOpt = userRepo.findById(booking.getPassengerId());
            passengerOpt.ifPresent(passenger -> {
                passenger.getBookingList().remove(booking);
                userRepo.save(passenger);
            });

            bookingRepo.delete(booking);
            return true;
        }
        return false;
    }


    public List<Booking> getBookingsForUser(String username) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        return userOpt.map(User::getBookingList).orElse(List.of());
    }


    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
}
