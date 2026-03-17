package com.project.uber.repository;


import com.project.uber.entity.RideAvailable;
import com.project.uber.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepositoryClass {
             @Autowired
              RideRepository rideRepository;
               @Autowired
               UserRepository userRepository;

      List<RideAvailable> rides = new ArrayList<>();

    public String processPayment(Long RideAvailableId , Long userId) {
       rides = rideRepository.findAll();
        User user =  userRepository.findById(userId).get(); // get se optional return ke ;liye lgaya he

        for(RideAvailable ride : rides){
            if(ride.getId().equals(RideAvailableId)){

                return "Payment of "+ ride.getFare()+" processed successfully for user id"+ user.getId()+" and  Ride ID: " + RideAvailableId + " using " + user.getPaymentMethod() + "for user"+user.getName()  +". Driver: " + ", Vehicle:  " ;
            }
        }
       return "Ride not found for ID: " + RideAvailableId;
    }
//    public void saveDetails(){
//        RideAvailable rideAvailable = new RideAvailable();
//
//        rideAvailable.setId(1L);
//        rideAvailable.setDriverName("John Doe");
//        rideAvailable.setClassOfVehicle("Sedan");
//        rideAvailable.setFare(200D);
//        rideAvailable.setDriverPhoneNumber("626858658");
//        rideAvailable.setVehicleNumber("MH12AB1234");
//        rideAvailable.setDriverRating("4.5");
//        rideAvailable.setCarType("toyota");
//        rideAvailable.setCarName("Camry");
//
//        RideAvailable rideAvailable2 = new RideAvailable();
//        rideAvailable2.setId(2L);
//        rideAvailable2.setDriverName("govind Doe");
//        rideAvailable2.setClassOfVehicle("kedan");
//        rideAvailable2.setFare(250D);
//        rideAvailable2.setDriverPhoneNumber("6658858658");
//        rideAvailable2.setVehicleNumber("MH12BC1354");
//        rideAvailable2.setDriverRating("4.3");
//        rideAvailable2.setCarType("suzuki");
//        rideAvailable2.setCarName("maruti");
//
//        rides.add(rideAvailable);
//        rides.add(rideAvailable2);
//    }
}
