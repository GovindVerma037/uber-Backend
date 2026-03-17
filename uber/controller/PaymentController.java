package com.project.uber.controller;


import com.project.uber.entity.Transaction;
import com.project.uber.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/method")
    public String getPaymentMethod(@RequestParam Long userId) {
        return paymentService.paymentMethod(userId);
    }
    @PostMapping("/process")
    public Transaction processPayment(@RequestParam Long rideBookedId) {
        return paymentService.processPayment( rideBookedId);
    }
}
