//package com.project.uber.repository;
//
//import com.project.uber.entity.RideAvailable;
//import com.project.uber.entity.RideRequest;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//           //@Transactional
//public class RideRepositoryClass {
//
////    @PersistenceContext
////    EntityManager entityManager;
//
//     public String saveRide(){
//
//         for(int i=0; i<2; i++) {
//
//
//             RideAvailable rideAvailable = new RideAvailable();
//
//            // rideAvailable.setId(1L); db khud banayega
//             rideAvailable.setDriverName("Driver "+i);
//             rideAvailable.setClassOfVehicle("Sedan");
//             rideAvailable.setFare(200D + i);
//             rideAvailable.setDriverPhoneNumber("626858658"+i);
//             rideAvailable.setVehicleNumber("MH12AB1234"+i);
//             rideAvailable.setDriverRating("4.5");
//             rideAvailable.setCarType("toyota");
//             rideAvailable.setCarName("Camry"+i);
//
//             entityManager.persist(rideAvailable);
//         }
//      return "ride saved succesfully";
//  }
//    public List<RideAvailable> findRides(RideRequest request) {
//         saveRide();
//     List<RideAvailable> rides = new ArrayList<>();
//        if(request==null || request.getDropLocation()==null || request.getPickupLocation()==null){
//           throw new IllegalArgumentException("Invalid ride request: missing pickup or drop location");
//        }else{
//            rides.add(entityManager.find(RideAvailable.class,1));
//            rides.add(entityManager.find(RideAvailable.class,2));
//            return rides;
//        }
//  }
//}
