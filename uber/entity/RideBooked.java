package com.project.uber.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RideBooked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private  Vehicle vehicle;



    private String pickupLocation;
    private String dropLocation;
    private Double fare;
    private String startTime;
    private String endTime;
    private String selectedPaymentMethod;
    private  String paymentStatus;
    private Double distance;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    public enum RideStatus {
        CANCELLED,
        CONFIRMED,
        PAYMENT_METHOD_PENDING,
        COMPLETED,

    }
}
