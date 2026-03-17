package com.project.uber.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Trip {

    @Id
    private  Long id;
    private String pickupLocation;
    private String dropLocation;
    private String distance;
    private double fare;
    private String startTime;
    private String endTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private  User user;
}
