package com.example.polyfood.controllers;

import com.example.polyfood.models.Product;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.services.ipm.ProductServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "api/version1.0")
public class ProductController {
    @Autowired
    ProductServices productServices;

    @GetMapping(value = "product/getall/{pageNumber}/{pageSize}/{field}")
    public Page<Product> getAll(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field)
    {
        if (field != null)
            return productServices.getAllProduct(pageNumber,pageSize, field);
        else
            return productServices.getAllProduct(pageNumber, pageSize, "productName");
    }

    @GetMapping(value = "product/getall/{pageNumber}/{pageSize}")
    public Page<Product> getAll(@PathVariable int pageNumber, @PathVariable int pageSize)
    {
        return productServices.getAllProduct(pageNumber,pageSize,null);
    }

    @PostMapping(value = "product/create")
    public Respon<Product> createProduct(@RequestBody String product)
    {
        Gson gson = new Gson();
        Product productNew = gson.fromJson(product,Product.class);
        return productServices.creatProduct(productNew);
    }
    @PutMapping(value = "product/update")
    public Respon<Product> updateProduct(@RequestBody String product)
    {
        Gson gson = new Gson();
        Product productNew = gson.fromJson(product,Product.class);
        return productServices.updatePrduct(productNew);
    }
    @DeleteMapping(value = "product/delete")
    public  Respon<Product> deleteProDuct(@RequestParam int idProduct)
    {
        return productServices.delete(idProduct);
    }
    @GetMapping(value = "product/seach/{pageNumber}/{pageSize}")
    public Page<Product> seachProduct(@RequestParam String name,@PathVariable int pageNumber,int pageSize)
    {
        return productServices.seachProduct(name,pageNumber,pageSize);
    }
    @GetMapping(value = "product/seachprice/{pageNumber}/{pageSize}")
    public Page<Product> seachPriceProduct(@RequestParam int value1, @RequestParam int value2, @PathVariable int pageNumber, @PathVariable int pageSize)
    {
        return productServices.seachByPrice(value1,value2,pageNumber,pageSize);
    }
}
