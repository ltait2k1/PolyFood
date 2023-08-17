package com.example.polyfood.services.ipm;

import com.example.polyfood.models.ProductType;
import com.example.polyfood.repository.IProductTypeRepository;
import com.example.polyfood.services.IProductTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductTypeServices implements IProductTypeServices {
    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Override
    public ProductType addProducType(ProductType productType) {
        productTypeRepository.save(productType);
        return productType;
    }
}
