package com.example.polyfood.services;

import com.example.polyfood.models.Payment;

import java.util.List;

public interface IPaymentServices {
    public List<Payment> getAllPayment(int pageSize, int pageNumber);
}
