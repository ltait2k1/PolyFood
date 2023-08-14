package com.example.polyfood.repository;

import com.example.polyfood.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductReviewRepository extends JpaRepository<ProductReview,Integer> {
}
