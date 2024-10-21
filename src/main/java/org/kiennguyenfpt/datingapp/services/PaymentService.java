package org.kiennguyenfpt.datingapp.services;

public interface PaymentService {
    void processPayment(Long userId, Double amount);
}
