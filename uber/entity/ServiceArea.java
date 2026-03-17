package com.project.uber.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ServiceArea {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String location;
     private  Long radius;



     @ManyToMany(mappedBy = "serviceAreas",cascade = CascadeType.ALL)
     private List<Driver> driver =  new ArrayList<>();
}
