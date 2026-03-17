package com.project.uber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String diverLicense;
    private int age;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

        @ManyToMany
        @JoinTable(
            name="driver_service_area",
            joinColumns = @JoinColumn(name="driver_id"),
            inverseJoinColumns = @JoinColumn(name="service_area_id")
        )
        private List<ServiceArea> serviceAreas = new ArrayList<>();

}
