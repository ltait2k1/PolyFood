package com.example.polyfood.services;

import com.example.polyfood.models.Order;
import com.example.polyfood.models.OrderDetail;
import com.example.polyfood.models.responobj.Respon;

import java.util.Set;

public interface IOrderServices {
    public Respon<Order> addOrder(int idCart, String fullName, String email, String phone, String address, String note);
    public Order viewOrder(int idOrder);
}
