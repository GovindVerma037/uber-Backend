package com.project.uber.controller;


import com.project.uber.entity.*;
import com.project.uber.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


     @Autowired
     private UserService userService;



    @GetMapping("/id/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/find/rides/{sortByField}")
    public List<RideAvailable> findRides(@RequestBody RideRequest rideRequest , @PathVariable String sortByField) {
      return userService.findRides(rideRequest,sortByField);
    }

    @PostMapping("/ride/book")
    public RideBooked bookRide(@RequestParam Long userId, @RequestParam Long rideAvailableId) {
        return userService.bookRide(userId, rideAvailableId);
    }

//    @GetMapping("/find/trip")
//    public List<Trip> findTripByDistance(@RequestParam double fare){
//        return userService.findTripByFare(fare);
//    }
//
//    @GetMapping("/find/trips")
//    public Page<Trip> findAllTrips(@RequestParam int page, @RequestParam int size, @RequestParam String sortByField){
//        return userService.findAllTrips(page,size,sortByField);
//    }
}
