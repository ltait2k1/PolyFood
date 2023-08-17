package com.example.polyfood.services;

import com.example.polyfood.models.Payment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPaymentServices {
    public Page<Payment> getAllPayment(int pageNumber, int pageSize);
}
