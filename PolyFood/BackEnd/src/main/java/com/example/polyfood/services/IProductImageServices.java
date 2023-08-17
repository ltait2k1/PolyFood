package com.example.polyfood.services;

import com.example.polyfood.models.ProductImage;
import com.example.polyfood.models.responobj.Respon;

public interface IProductImageServices {
    public Respon<ProductImage> addProductImage(ProductImage productImage);
    public Respon<ProductImage> updateProductImage(ProductImage productImage);
    public Respon<ProductImage> deleteProductImage(int producImageId);
}
