package com.example.polyfood.services.ipm;

import com.example.polyfood.models.ProductImage;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.IProductImageRepository;
import com.example.polyfood.services.IProductImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProductImageServices implements IProductImageServices {
    @Autowired
    private IProductImageRepository productImageRepository;

    private static Respon<ProductImage> respon = new Respon<>();

    @Override
    public Respon<ProductImage> addProductImage(ProductImage productImage) {
        Optional<ProductImage> optional = productImageRepository.findById(productImage.getProduct_Image_Id());
        if (optional.isEmpty()){
            Date createdTime = new Date();
            productImage.setUpdateAt(createdTime);
            productImage.setUpdateAt(createdTime);
            productImageRepository.save(productImage);
            respon.setData(productImage);
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
    public Respon<ProductImage> updateProductImage(ProductImage productImageNew) {
        Optional<ProductImage> optional = productImageRepository.findById(productImageNew.getProduct_Image_Id());
        if (optional.isPresent()){
            ProductImage productImage = productImageRepository.getReferenceById(productImageNew.getProduct_Image_Id());
            productImageNew.setUpdateAt(productImage.getUpdateAt());
            Date updateTime = new Date();
            productImageNew.setUpdateAt(updateTime);
            productImageRepository.save(productImageNew);
            respon.setData(productImageNew);
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
    public Respon<ProductImage> deleteProductImage(int producImageId) {
        Optional<ProductImage> optional = productImageRepository.findById(producImageId);
        if (optional.isPresent()){
            ProductImage productImage = productImageRepository.getReferenceById(producImageId);
            productImageRepository.delete(productImage);
            respon.setData(productImage);
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
