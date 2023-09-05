package com.example.polyfood.services.ipm;

import com.example.polyfood.models.CartItem;
import com.example.polyfood.models.Order;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.ICartRepository;
import com.example.polyfood.repository.IOrderRepository;
import com.example.polyfood.services.IOrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServices implements IOrderServices {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ICartRepository cartRepository;

    private static Respon<Order> respon = new Respon<>();

    @Override
    public Respon<Order> addOrder(int idCart) {
        List<CartItem> cartItems = cartRepository.getReferenceById(idCart).getCartItems().stream().toList();
        Order orderNew = new Order();
        return respon;
    }
}
