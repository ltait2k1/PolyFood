package com.example.polyfood.services.ipm;

import com.example.polyfood.models.*;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.*;
import com.example.polyfood.services.IOrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServices implements IOrderServices {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IOrderStatusRepository orderStatusRepository;

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    private static Respon<Order> respon = new Respon<>();

    private double priceTotal(Product product, int quantity){
        double priceTotal = (product.getPrice() * ((100.0 - product.getDiscount())/100)) * quantity;
        return priceTotal;
    }

    private void updateOrder(int idOrder){
        Order order = orderRepository.getReferenceById(idOrder);
        double actualPrice = 0;
        double originalPrice = 0;
        for (OrderDetail orderDetail: orderDetailRepository.findAll()){
            if (orderDetail.getOrder().getOrderId() == idOrder){
                actualPrice += (orderDetail.getProduct().getPrice() * orderDetail.getQuantity());
                originalPrice += orderDetail.getPriceTotal();
            }
        }
        order.setActualPrice(actualPrice);
        order.setOriginalPrice(originalPrice);
        Date date = new Date();
        order.setUpdateAt(date);
        orderRepository.save(order);
    }

    @Override
    public Respon<Order> addOrder(int idCart) {
        Cart cart = cartRepository.getReferenceById(idCart);
        OrderStatus orderStatus = orderStatusRepository.getReferenceById(1);
        Payment payment = paymentRepository.getReferenceById(1);
        Order orderNew = new Order();
        Date date = new Date();
        orderNew.setCreatedAt(date);
        orderNew.setUpdateAt(date);
        orderNew.setOrderStatus(orderStatus);
        orderNew.setPayment(payment);
        orderNew.setUser(cart.getUser());
        orderRepository.save(orderNew);

        Set<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem: cartItems){
            OrderDetail orderDetail = new OrderDetail();
            Product product = cartItem.getProduct();
            orderDetail.setOrder(orderNew);
            orderDetail.setCreatedAt(date);
            orderDetail.setUpdateAt(date);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPriceTotal(priceTotal(product,cartItem.getQuantity()));
            orderDetailRepository.save(orderDetail);
        }
        updateOrder(orderNew.getOrderId());
        respon.setData(orderNew);
        respon.setStatus(200);
        respon.setMassage("ok");
        return respon;
    }

    @Override
    public Set<OrderDetail> getAll(int idOrder) {
        Order order = orderRepository.getReferenceById(idOrder);
        Set<OrderDetail> orderDetails = order.getOrderDetails();
        return orderDetails;
    }
}
