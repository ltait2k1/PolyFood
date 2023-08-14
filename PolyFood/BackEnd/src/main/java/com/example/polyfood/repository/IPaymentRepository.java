package com.example.polyfood.repository;

import com.example.polyfood.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment,Integer> {
}
