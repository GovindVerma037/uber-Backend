package com.project.uber.services;

import com.project.uber.entity.Trip;
import com.project.uber.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    @Autowired
    TripRepository tripRepository;

    public Trip createTrip(Long rideBookedId, Long userId) {
        Trip trip = new Trip();

        return tripRepository.save(trip);
    }

    public List<Trip> findTripByFare(double fare) {
      return tripRepository.findTripByFare(fare);
    }

    public Page<Trip> findAllTrips(int page, int size, String sortByField) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortByField));
        return tripRepository.findAll(pageable);
    }
}
