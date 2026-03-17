package com.project.uber.controller;

import com.project.uber.entity.Trip;
import com.project.uber.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {
    @Autowired
    TripService tripService;

    @PostMapping("/create")
    public Trip createTrip(@RequestParam Long rideBookedId , @RequestParam Long userId){
        return tripService.createTrip(rideBookedId,userId) ;

    }

    @GetMapping("/find")
    List<Trip> findTripByFare(@RequestParam double fare){
        return tripService.findTripByFare(fare);
    }

    @GetMapping("/find/trips")
    public Page<Trip> findAllTrips(@RequestParam int page, @RequestParam int size, @RequestParam String sortByField){
        return tripService.findAllTrips(page,size,sortByField);
    }

}
