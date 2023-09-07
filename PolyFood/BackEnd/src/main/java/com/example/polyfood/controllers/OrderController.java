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

    @PostMapping(value = "order/addOrder/{idOrder}/{fullName}/{email}/{phone}/{address}/{note}")
    public Respon<Order> addOrder(
            @PathVariable int idOrder,
            @PathVariable String fullName,
            @PathVariable String email,
            @PathVariable String phone,
            @PathVariable String address,
            @PathVariable String note){
        return orderServices.addOrder(idOrder,fullName,email,phone,address,note);
    }

    @GetMapping(value = "order/viewOrder/{idOrder}")
    public Order viewOrder(@PathVariable int idOrder){
        return orderServices.viewOrder(idOrder);
    }
}
