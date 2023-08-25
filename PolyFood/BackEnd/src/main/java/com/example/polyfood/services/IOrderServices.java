package com.example.polyfood.services;

import com.example.polyfood.models.Order;
import com.example.polyfood.models.responobj.Respon;

public interface IOrderServices {
    public Respon<Order> addOrder(Order order);
}
