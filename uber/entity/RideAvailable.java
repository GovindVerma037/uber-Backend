package com.project.uber.entity;

//getter setter ke liye use lombok dependency

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter  dono ki jagah data ka use karlo
//@Setter
@Data
@Entity
public class RideAvailable {
    @Id  // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double fare;
//    String classOfVehicle;
//    String vehicleNumber;
//    String driverName;
//    String driverPhoneNumber;
//    String driverRating;
//    String carType;
//    String carName;

     String dropLocation;
     String pickupLocation;

     @OneToOne(cascade = CascadeType.ALL)
     private Vehicle vehicle;
}
