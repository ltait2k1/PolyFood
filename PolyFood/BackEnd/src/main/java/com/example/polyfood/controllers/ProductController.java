package com.example.polyfood.controllers;

import com.example.polyfood.models.Product;
import com.example.polyfood.services.ipm.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "api/version1.0")
public class ProductController {
    @Autowired
    ProductServices productServices;
    @GetMapping(value = "product/getall/{pageNumber}/{pageSize}")
    public Page<Product> getAll(@PathVariable int pageNumber, @PathVariable int pageSize)
    {
        return productServices.getAllProduct(pageNumber,pageSize);
    }
}
