package com.project.uber.entity;

import lombok.Data;

@Data
public class RideRequest {

    String dropLocation;
    String pickupLocation;
}
