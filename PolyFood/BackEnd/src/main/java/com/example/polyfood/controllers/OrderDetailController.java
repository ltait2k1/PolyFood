package com.example.polyfood.controllers;

import com.example.polyfood.models.OrderDetail;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.services.IOrderDetailServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "http://localhost:8080")

@RestController
@RequestMapping(value = "api/version1.0")
public class OrderDetailController {
    @Autowired
    private IOrderDetailServices orderDetailServices;

    @PostMapping(value = "orderDetail/addOrderDetail")
    public Respon<OrderDetail> addOrderDetail(@RequestBody String orderDetailNew){
        Gson gson = new Gson();
        OrderDetail orderDetail = gson.fromJson(orderDetailNew, OrderDetail.class);
        return orderDetailServices.addOrderDetail(orderDetail);
    }
}
