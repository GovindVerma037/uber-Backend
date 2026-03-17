package com.project.uber.services;

import com.project.uber.entity.*;
import com.project.uber.repository.RoleRepository;
import com.project.uber.repository.TripRepository;
import com.project.uber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
   @Autowired
    private RideService rideService;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
  private  PasswordEncoder passwordEncoder;
 @Autowired
   private RoleRepository roleRepository;

    public String register(User user) {
        Role role = new Role();


        if(user.getRole().getRoleName().equalsIgnoreCase("ADMIN")){
            role.setRoleName("ROLE_ADMIN");
        } else if(user.getRole().getRoleName().equalsIgnoreCase("DRIVER")){
            role.setRoleName("ROLE_DRIVER");
        } else {
            role.setRoleName("ROLE_USER");
        }
        Role savedRole = roleRepository.save(role);
        user.setRole(savedRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(userRepository.save(user).getId()!=null){
            return "User register succesfully with id "+user.getId();
        }
      return "user registration failed ";
    }

    public String login(String email,String password){
        for(User user : userRepository.findAll()){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return "Login successful for user "+user.getName();
            }
        }
        return "login failed ! Invalid email or password ";
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<RideAvailable> findRides(RideRequest rideRequest,String sortByField) {
         return rideService.getRideDetails(rideRequest,sortByField);
    }

    public RideBooked bookRide(Long userId, Long rideAvailableId) {
     return rideService.bookAvailableRide(userId,rideAvailableId);
    }

//    public List<Trip> findTripByFare(double fare) {
//        return tripRepository.findTripByFare(fare);
//    }
//
//    public Page<Trip> findAllTrips(int page,int size , String sortByField){
//
//        Pageable pageable = PageRequest.of(page,size, Sort.by(sortByField));
//        return tripRepository.findAll(pageable);
//    }
}
