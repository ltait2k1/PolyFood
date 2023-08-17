package com.example.polyfood.repository;

import com.example.polyfood.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOderRepository extends JpaRepository<Order,Integer> {
}
