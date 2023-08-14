package com.example.polyfood.repository;

import com.example.polyfood.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
