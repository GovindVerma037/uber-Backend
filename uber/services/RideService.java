package com.project.uber.services;
import com.project.uber.entity.*;
import com.project.uber.repository.RideBookedRepository;
import com.project.uber.repository.RideRepository;
import com.project.uber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;
     @Autowired
     private PaymentService paymentService;
   @Autowired
   private UserRepository userRepository;
    @Autowired
    private DriverService driverService;
    @Autowired
    private RideBookedRepository rideBookedRepository;

    public String saveRideDeatils(){

       Vehicle vehicle = new Vehicle();
       vehicle.setVehicleType("Sedan");
       vehicle.setModel("camry");
       vehicle.setVehicleNumber("MH12AB1234");
       vehicle.setFuelType("patrol");
       vehicle.setColor("white");
       vehicle.setSeatingCapacity(5);
     vehicle.setVehicleClass("Uber6");

       RideAvailable rideAvailable = new RideAvailable();
       rideAvailable.setFare(225D);
       rideAvailable.setDropLocation("bhopal");
       rideAvailable.setPickupLocation("Indore");

       rideAvailable.setVehicle(vehicle);
       rideRepository.save(rideAvailable);
       return "ride saved succesfully";
   }
    public List<RideAvailable> getRideDetails(RideRequest request,String sortByField) {
        User user = userRepository.findById(1L).get();
       // System.out.println(user.getTrips().get(0).getPickupLocation());

        if(request==null || request.getDropLocation()==null || request.getPickupLocation()==null){
           throw new IllegalArgumentException("Invalid ride request: missing pickup or drop location");
        }else{

//            List<RideAvailable> rides = rideRepository.findAll();
//            for(RideAvailable ride : rides){
//                if(ride.getPickupLocation().equalsIgnoreCase(request.getPickupLocation())&&ride.getDropLocation().equalsIgnoreCase(request.getDropLocation())){
//                    return rides;
//                }
//            }
            //sorting
            Sort sort =  Sort.by(sortByField).ascending();



              // derived query
            List<RideAvailable> rides = rideRepository.findByDropLocationAndPickupLocation(request.getDropLocation(), request.getPickupLocation(),sort);
          if(!rides.isEmpty()){
              return rides;
          }
        }
        System.out.println("no rides found ");
  return List.of();
    }

    public RideBooked bookAvailableRide(Long rideId, Long userId) {

      RideAvailable rideAvailable = rideRepository.findById(rideId).orElseThrow();
      User user = userRepository.findById(userId).orElseThrow();

      RideBooked rideBooked =  new RideBooked();
        System.out.println("Initiating payment seletion for user id "+userId+" and Ride ID: " + rideId);

       String paymentMethod=  paymentService.paymentMethod(user.getId());
       if(!paymentMethod.isEmpty()){
                rideBooked.setRideStatus(RideBooked.RideStatus.CONFIRMED);
                rideBooked.setSelectedPaymentMethod(paymentMethod);
                rideBooked.setPickupLocation(rideAvailable.getPickupLocation());
                rideBooked.setDropLocation(rideAvailable.getDropLocation());
                rideBooked.setFare(rideAvailable.getFare());
                rideBooked.setPaymentStatus("PAYMENT_PENDING");
                rideBooked.setStartTime("10:00");
                rideBooked.setEndTime("11:00");
                rideBooked.setDistance(30D);

               rideBooked.setUser(user);
               rideBooked.setDriver(driverService.findByVehicleId(rideAvailable.getVehicle().getId()));
               rideBooked.setVehicle(rideAvailable.getVehicle());

       }else rideBooked.setRideStatus(RideBooked.RideStatus.PAYMENT_METHOD_PENDING);

       rideBookedRepository.save(rideBooked);
       userRepository.save(user);
       return rideBooked;
//      if(paymentStatus.contains("processed successfully for user id")){
//          Trip  trip = new Trip();
//          trip.setPickupLocation(rideAvailable.getPickupLocation());
//          trip.setDropLocation(rideAvailable.getDropLocation());
//          trip.setFare(rideAvailable.getFare());
//          trip.setStartTime("10:00");
//          trip.setEndTime("11.00");
//          trip.setDistance("30km");
//              trip.setUser(user);
//
//          List<Trip> trips = new ArrayList<>();
//      //  trips.add(trip);
//             user.setTrips(trips);
//          userRepository.save(user);
//
//      }else{
//            throw new RuntimeException("Payment failed for user id "+userId+" and Ride ID: " + rideAvailableId);
//         }
//
//        Driver d = new Driver();
//      d.setVehicle(rideAvailable.getVehicle());
//        return d;

      }


    public RideBooked cancelRide(Long rideId) {
       RideBooked rideBooked = rideBookedRepository.findById(rideId).orElseThrow(()-> new RuntimeException("Ride not found with id "+rideId));
       if(rideBooked.getRideStatus().equals(RideBooked.RideStatus.CONFIRMED)) {
           rideBooked.setRideStatus(RideBooked.RideStatus.CANCELLED);
           rideBookedRepository.save(rideBooked);
       }

        return new RideBooked(); // return a response indicating the ride has been cancelled
    }
}
