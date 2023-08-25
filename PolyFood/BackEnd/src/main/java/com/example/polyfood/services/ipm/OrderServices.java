package com.example.polyfood.services.ipm;

import com.example.polyfood.models.Order;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.IOrderRepository;
import com.example.polyfood.services.IOrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServices implements IOrderServices {
    @Autowired
    private IOrderRepository orderRepository;

    private static Respon<Order> respon = new Respon<>();

    @Override
    public Respon<Order> addOrder(Order order) {
        Optional<Order> optional = orderRepository.findById(order.getOrderId());
        if (optional.isEmpty()){
            Date date = new Date();
            order.setCreatedAt(date);
            order.setUpdateAt(date);
            orderRepository.save(order);
            respon.setData(order);
            respon.setStatus(200);
            respon.setMassage("Them thanh cong");
        }
        return respon;
    }
}
