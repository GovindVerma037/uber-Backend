package com.project.uber.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String vehicleType;
    private String model;
    private String color;
    private int seatingCapacity;
    private String vehicleNumber;
    private  String fuelType;
    private String vehicleClass;
}
