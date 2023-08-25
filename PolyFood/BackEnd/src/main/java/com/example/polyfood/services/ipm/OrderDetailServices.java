package com.example.polyfood.services.ipm;

import com.example.polyfood.models.Order;
import com.example.polyfood.models.OrderDetail;
import com.example.polyfood.models.Product;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.IOrderDetailRepository;
import com.example.polyfood.repository.IOrderRepository;
import com.example.polyfood.repository.IProductRepository;
import com.example.polyfood.services.IOrderDetailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderDetailServices implements IOrderDetailServices {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductRepository productRepository;

    private static Respon<OrderDetail> respon = new Respon<>();

    @Override
    public Respon<OrderDetail> addOrderDetail(OrderDetail orderDetail) {
        Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetail.getOrderDetailId());
        if (optional.isEmpty()){
            Date date = new Date();
            orderDetail.setCreatedAt(date);
            orderDetail.setUpdateAt(date);
            Product product = productRepository.getReferenceById(orderDetail.getProduct().getProductId());
            double priceTotal = ((product.getPrice() * ((100 - product.getDiscount())/100)) * orderDetail.getQuantity());
            orderDetail.setPriceTotal(priceTotal);
            orderDetailRepository.save(orderDetail);
            Order order = orderRepository.getReferenceById(orderDetail.getOrder().getOrderId());
            order.setUpdateAt(date);
            double originalPriceTotal = product.getPrice() * orderDetail.getQuantity();
            order.setOriginalPrice(order.getOriginalPrice() + originalPriceTotal);
            order.setActualPrice(order.getOriginalPrice() + priceTotal);
            orderRepository.save(order);
            respon.setData(orderDetail);
            respon.setStatus(200);
            respon.setMassage("them thanh cong");
        }
        else {
            respon.setMassage("id da ton tai");
        }
        return respon;
    }
}
