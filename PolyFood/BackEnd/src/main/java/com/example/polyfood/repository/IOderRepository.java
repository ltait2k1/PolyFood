package com.example.polyfood.repository;

import com.example.polyfood.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOderRepository extends JpaRepository<Order,Integer> {
}
