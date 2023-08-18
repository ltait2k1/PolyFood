package com.example.polyfood.services;

import com.example.polyfood.models.Product;
import com.example.polyfood.models.responobj.Respon;
import org.springframework.data.domain.Page;

public interface IProductServices {
    public Page<Product> getAllProduct(int pageNumber, int pageSize);
    public Respon<Product> creatProduct(Product product);
    public Respon<Product> updatePrduct(Product product);
    public Respon<Product> delete(int idProduct);
}
