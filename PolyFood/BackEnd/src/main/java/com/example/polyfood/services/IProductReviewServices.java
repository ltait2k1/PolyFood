package com.example.polyfood.services;

import com.example.polyfood.models.ProductReview;
import com.example.polyfood.models.responobj.Respon;
import org.springframework.data.domain.Page;

public interface IProductReviewServices {
    public Page<ProductReview> getAllProductReview(int pageNumber, int pageSize);
    public Respon<ProductReview> addProductReview(ProductReview productReview);
    public Respon<ProductReview> updateProductReview(ProductReview productReview);
    public Respon<ProductReview> deleteProductReview(int productReviewId);
}
