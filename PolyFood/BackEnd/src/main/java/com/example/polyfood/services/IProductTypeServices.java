package com.example.polyfood.services;

import com.example.polyfood.models.ProductType;
import com.example.polyfood.models.responobj.Respon;
import org.springframework.data.domain.Page;

public interface IProductTypeServices {
    public Page<ProductType> getAll(int pageNumber,int pageSize);
    public Respon<ProductType> addProductType(ProductType productType);
    public Respon<ProductType> updateProductType(ProductType productType);
    public Respon<ProductType> deleteProductType(int productTypeId);
    public Page<ProductType> seachProduct(String name, int pageNumber, int pageSize);

}
