package com.project.uber.services;

import com.project.uber.entity.RideBooked;
import com.project.uber.entity.Transaction;
import com.project.uber.entity.Trip;
import com.project.uber.repository.PaymentRepository;
import com.project.uber.repository.RideBookedRepository;
import com.project.uber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
   @Autowired
   UserRepository userRepository;
  @Autowired
    TripService tripService;
  @Autowired
  RideBookedRepository rideBookedRepository;

    public Transaction processPayment(Long rideBookedId) {
      int counter = 0;
      ++counter;

        RideBooked rideBooked = rideBookedRepository.findById(rideBookedId).get();
        rideBooked.setRideStatus(RideBooked.RideStatus.COMPLETED);
        rideBooked.setPaymentStatus("Completed");
        rideBookedRepository.save(rideBooked);

        Trip trip = tripService.createTrip(rideBookedId, rideBooked.getUser().getId());
        Transaction transaction = new Transaction();
        transaction.setPaymentMethod(rideBooked.getSelectedPaymentMethod());
        transaction.setAmount(rideBooked.getFare());
        transaction.setStatus("Succesfull");
        transaction.setTrip(trip);
        transaction.setTxnId(counter+2456L);
        return paymentRepository.save(transaction);
    }

    public String paymentMethod(Long userId) {
        return userRepository.findById(userId).get().getPaymentMethod();
    }
}
