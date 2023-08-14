package com.example.polyfood.repository;

import com.example.polyfood.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductImageRepository extends JpaRepository<ProductImage,Integer> {
}
