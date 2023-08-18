package com.example.polyfood.services.ipm;

import com.example.polyfood.models.ProductReview;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.IProductReviewRepository;
import com.example.polyfood.services.IProductReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProductReviewServices implements IProductReviewServices {
    @Autowired
    private IProductReviewRepository productReviewRepository;

    private static Respon<ProductReview> respon = new Respon<>();

    @Override
    public Respon<ProductReview> addProductReview(ProductReview productReviewNew) {
        Optional<ProductReview> optional = productReviewRepository.findById(productReviewNew.getProductReviewId());
        if (optional.isEmpty()){
            Date createdTime = new Date();
            productReviewNew.setCreatedAt(createdTime);
            productReviewNew.setUpdateAt(createdTime);
            productReviewRepository.save(productReviewNew);
            respon.setData(productReviewNew);
            respon.setStatus(200);
            respon.setMassage("Them thanh cong");
        }
        else {
            respon.setStatus(404);
            respon.setMassage("Id da ton tai");
        }
        return respon;
    }

    @Override
    public Respon<ProductReview> updateProductReview(ProductReview productReviewNew) {
        Optional<ProductReview> optional = productReviewRepository.findById(productReviewNew.getProductReviewId());
        if (optional.isPresent()){
            ProductReview productReview = productReviewRepository.getReferenceById(productReviewNew.getProductReviewId());
            productReviewNew.setCreatedAt(productReview.getCreatedAt());
            Date updateTime = new Date();
            productReviewNew.setUpdateAt(updateTime);
            productReviewRepository.save(productReviewNew);
            respon.setData(productReviewNew);
            respon.setStatus(200);
            respon.setMassage("Them thanh cong");
        }
        else {
            respon.setStatus(404);
            respon.setMassage("Id khong ton tai");
        }
        return respon;
    }

    @Override
    public Respon<ProductReview> deleteProductReview(int productReviewId) {
        Optional<ProductReview> optional = productReviewRepository.findById(productReviewId);
        if (optional.isPresent()){
            ProductReview productReview = productReviewRepository.getReferenceById(productReviewId);
            productReviewRepository.delete(productReview);
            respon.setStatus(200);
            respon.setMassage("Xoa thanh cong");
        }
        else {
            respon.setStatus(404);
            respon.setMassage("Id khong ton tai");
        }
        return respon;
    }
}
