package com.example.polyfood.repository;

import com.example.polyfood.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<Product,Integer> {
}
