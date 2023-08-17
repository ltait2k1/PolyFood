package com.example.polyfood.services.ipm;

import com.example.polyfood.models.ProductType;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.IProductTypeRepository;
import com.example.polyfood.services.IProductTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class ProductTypeServices implements IProductTypeServices {
    @Autowired
    private IProductTypeRepository productTypeRepository;

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
}
