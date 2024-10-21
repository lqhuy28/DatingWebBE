package org.kiennguyenfpt.datingapp.services.impl;

import org.kiennguyenfpt.datingapp.entities.User;
import org.kiennguyenfpt.datingapp.repositories.UserRepository;
import org.kiennguyenfpt.datingapp.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final UserRepository userRepository;

    public PaymentServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void processPayment(Long userId, Double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        // Logic to process payment (e.g., integrate with payment gateway)
        System.out.println("Processing payment of " + amount + " for user " + user.getEmail());
    }

}
