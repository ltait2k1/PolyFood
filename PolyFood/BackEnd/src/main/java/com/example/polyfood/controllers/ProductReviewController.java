package com.example.polyfood.controllers;

import com.example.polyfood.models.ProductReview;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.services.IProductReviewServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(originPatterns = "http://localhost:8080")

@RestController
@RequestMapping(value = "api/version1.0")
public class ProductReviewController {
    @Autowired
    private IProductReviewServices productReviewServices;

    @GetMapping(value = "productReview/getAll/{pageNumber}/{pageSize}")
    public Page<ProductReview> getAll(@PathVariable int pageNumber, @PathVariable int pageSize){
        return productReviewServices.getAllProductReview(pageNumber,pageSize);
    }

    @PostMapping(value = "productReview/addProductReview")
    public Respon<ProductReview> addProductReview(@RequestBody String productReviewNew){
        Gson gson = new Gson();
        ProductReview productReview = gson.fromJson(productReviewNew, ProductReview.class);
        return productReviewServices.addProductReview(productReview);
    }

    @PutMapping(value = "productReview/updateProductReview")
    public Respon<ProductReview> updateProductReview(@RequestBody String productReviewNew){
        Gson gson = new Gson();
        ProductReview productReview = gson.fromJson(productReviewNew, ProductReview.class);
        return productReviewServices.updateProductReview(productReview);
    }

    @DeleteMapping(value = "productReview/deleteProductReview")
    public Respon<ProductReview> deleteProductReview(@RequestParam int productReviewId ){
        return productReviewServices.deleteProductReview(productReviewId);
    }
}
