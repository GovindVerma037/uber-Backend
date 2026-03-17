package com.project.uber.services;


import com.project.uber.entity.Driver;
import com.project.uber.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

      @Autowired
      private DriverRepository driverRepository;

    public String registerDriver(Driver driver) {
        if(driverRepository.save(driver).getId()!=null){;
            return "Driver registered successfully with id "+driver.getId()+" and name "+driver.getName();
        }
        return "Driver registered failed";
    }

    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId).orElseThrow(()-> new RuntimeException("Driver not found with id "+driverId));
    }

    public List< Driver> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        if (drivers.isEmpty()) {
            throw new RuntimeException("No drivers found");
        }
     return drivers;}

    public Driver findByVehicleId(Long id) {
        return driverRepository.findById(id).orElseThrow(()-> new RuntimeException("Driver not found with vehicle id "+id));
    }
}
