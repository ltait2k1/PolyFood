package com.example.polyfood.services.ipm;

import com.example.polyfood.models.Payment;
import com.example.polyfood.services.IPaymentServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServices implements IPaymentServices {
    @Override
    public List<Payment> getAllPayment(int pageSize, int pageNumber) {
        return null;
    }
}
