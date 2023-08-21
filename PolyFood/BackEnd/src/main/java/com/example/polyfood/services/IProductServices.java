package com.example.polyfood.services;

import com.example.polyfood.models.Product;
import com.example.polyfood.models.responobj.Respon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface IProductServices {
    public Slice<Product> getAllProduct(int pageNumber, int pageSize, String field);
    public Respon<Product> creatProduct(Product product);
    public Respon<Product> updatePrduct(Product product);
    public Respon<Product> delete(int idProduct);
    public Page<Product> seachProduct(String name,int pageNumber, int pageSize);
    public Page<Product> seachByPrice(int value1, int value2, int pageNumber, int pageSize);
}
