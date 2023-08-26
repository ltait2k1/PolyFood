package com.example.polyfood.services;

import com.example.polyfood.models.OrderDetail;
import com.example.polyfood.models.responobj.Respon;

public interface IOrderDetailServices {
    public Respon<OrderDetail> addOrderDetail(OrderDetail orderDetail);
    public Respon<OrderDetail> updateOrderDetail(OrderDetail orderDetail);
}
