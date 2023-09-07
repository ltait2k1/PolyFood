package com.example.polyfood.controllers;

import com.example.polyfood.models.Order;
import com.example.polyfood.models.OrderDetail;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.services.IOrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@CrossOrigin(originPatterns = "http://localhost:8080")

@RestController
@RequestMapping(value = "api/version1.0")
public class OrderController {
    @Autowired
    private IOrderServices orderServices;

    @PostMapping(value = "order/addOrder/{idOrder}")
    public Respon<Order> addOrder(@PathVariable int idOrder){
        return orderServices.addOrder(idOrder);
    }

    @GetMapping(value = "order/getAll/{idOrder}")
    public Set<OrderDetail> getAll(@PathVariable int idOrder){
        return orderServices.getAll(idOrder);
    }
}
