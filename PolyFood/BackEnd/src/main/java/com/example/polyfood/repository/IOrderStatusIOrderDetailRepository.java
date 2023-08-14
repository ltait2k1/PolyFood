package com.example.polyfood.repository;

import com.example.polyfood.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderStatusIOrderDetailRepository extends JpaRepository<OrderStatus,Integer> {
}
