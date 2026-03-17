package com.project.uber.controller;


import com.project.uber.entity.Driver;
import com.project.uber.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;


    @PostMapping("/register")
    public String registerDriver(@RequestBody Driver driver) {
        return  driverService.registerDriver(driver);

    }
    @PreAuthorize("hasRole('DRIVER')")
        @GetMapping("/id/{driverId}")
        public Driver getDriverById(@PathVariable Long driverId) {
            return driverService.getDriverById(driverId);
        }
  @GetMapping("/vehicle/{vehicleId}")
    public Driver getDriverByVehicleId(@PathVariable Long vehicleId) {
        return driverService.findByVehicleId(vehicleId);
    }
    @GetMapping("/Alldrivers")
    public List< Driver> getDrivers() {

        return driverService.getAllDrivers();
    }
}
