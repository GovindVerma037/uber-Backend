package com.project.uber.repository;

import com.project.uber.entity.RideAvailable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<RideAvailable,Long> {
   // derived query method to find all rides by driver id

    List<RideAvailable> findByDropLocationAndPickupLocation(String dropLocation, String pickupLocation, Sort sort);





    // methods to find rides id or name
        // List<RideAvailable> findBy    DriverId  (Long driverId);
    //    List<RideAvailable> findBy   DriverName (String driverName);

}
