package com.example.polyfood.services.ipm;

import com.example.polyfood.models.*;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.*;
import com.example.polyfood.services.IProductTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ProductTypeServices implements IProductTypeServices {
    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IProductImageRepository productImageRepository;

    @Autowired
    private IProductReviewRepository productReviewRepository;

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    private static Respon<ProductType> respon = new Respon<>();



    @Override
    public Page<ProductType> getAll(int pageNumber,int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return productTypeRepository.findAll(pageable);
    }

    @Override
    public Respon<ProductType> addProductType(ProductType productType) {
        Optional<ProductType> optionalProductType = productTypeRepository.findById(productType.getProductTypeId());
        if (optionalProductType.isEmpty()){
            Date createdTime = new Date();
            productType.setCreatedAt(createdTime);
            productType.setUpdateAt(createdTime);
            productTypeRepository.save(productType);
            respon.setData(productType);
            respon.setStatus(200);
            respon.setMassage("Them moi thanh cong");
        }
        else {
            respon.setStatus(404);
            respon.setMassage("id da ton tai");
        }
        return respon;
    }

    @Override
    public Respon<ProductType> updateProductType(ProductType productTypeNew) {
        Optional<ProductType> optionalProductType = productTypeRepository.findById(productTypeNew.getProductTypeId());
        if (optionalProductType.isPresent()){
            ProductType productType = productTypeRepository.getReferenceById(productTypeNew.getProductTypeId());
            productTypeNew.setCreatedAt(productType.getCreatedAt());
            Date updateTime = new Date();
            productTypeNew.setUpdateAt(updateTime);
            productTypeRepository.save(productTypeNew);
            respon.setData(productTypeNew);
            respon.setStatus(200);
            respon.setMassage("Update thanh cong");
        }
        else {
            respon.setStatus(404);
            respon.setMassage("id khong ton tai");
        }
        return respon;
    }

    @Override
    public Respon<ProductType> deleteProductType(int productTypeId) {
        Optional<ProductType> optionalProductType = productTypeRepository.findById(productTypeId);
        if (optionalProductType.isEmpty()){
            respon.setStatus(404);
            respon.setMassage("id khong ton tai");
        }
        else {
            for (Product product: productRepository.findAll()){
                if (product.getProductType().getProductTypeId() == productTypeId){
                    for (ProductImage productImage: productImageRepository.findAll()){
                        if (productImage.getProduct().getProductId() == product.getProductId()){
                            productImageRepository.delete(productImage);
                        }
                    }

                    for (ProductReview productReview: productReviewRepository.findAll()){
                        if (productReview.getProduct().getProductId() == product.getProductId()){
                            productReviewRepository.delete(productReview);
                        }
                    }

                    for (OrderDetail orderDetail: orderDetailRepository.findAll()){
                        if (orderDetail.getProduct().getProductId() == product.getProductId()){
                            orderDetailRepository.delete(orderDetail);
                        }
                    }
                    productRepository.delete(product);
                }
            }
            ProductType productType = productTypeRepository.getReferenceById(productTypeId);
            productTypeRepository.delete(productType);
            respon.setStatus(200);
            respon.setMassage("xoa thanh cong");
        }
        return respon;
    }

    @Override
    public Page<ProductType> seachProduct(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return productTypeRepository.findAllByNameProuctTypeContains(name,pageable);
    }
}
