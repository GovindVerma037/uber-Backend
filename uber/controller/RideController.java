package com.project.uber.controller;

import com.project.uber.entity.RideAvailable;
import com.project.uber.entity.RideBooked;
import com.project.uber.entity.RideRequest;
import com.project.uber.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/ride")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/book")
    public RideBooked bookRide(@RequestParam Long rideId,@RequestParam Long userId) {
        return rideService.bookAvailableRide(rideId,userId);
    }
    @PutMapping("/cancel/{rideId}")
    public RideBooked cancelRide(@PathVariable Long rideId) {
        return rideService.cancelRide(rideId);
    }
        @GetMapping("/find/{sortByField}")
    public List<RideAvailable> findRides(@RequestBody RideRequest rideRequest , @PathVariable String sortByField) {
       return rideService.getRideDetails(rideRequest,sortByField);
    }

//    @GetMapping("/details")
//    public List<RideAvailable> fetchRideDetails(@RequestBody RideRequest request) {
//       return rideService.getRideDetails(request,"fare");
//    }

}
