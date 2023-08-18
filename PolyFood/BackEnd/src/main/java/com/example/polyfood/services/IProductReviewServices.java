package com.example.polyfood.services;

import com.example.polyfood.models.ProductReview;
import com.example.polyfood.models.responobj.Respon;

public interface IProductReviewServices {
    public Respon<ProductReview> addProductReview(ProductReview productReview);
    public Respon<ProductReview> updateProductReview(ProductReview productReview);
    public Respon<ProductReview> deleteProductReview(int productReviewId);
}
