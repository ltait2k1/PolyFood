package com.example.polyfood.services.ipm;

import com.example.polyfood.models.OrderDetail;
import com.example.polyfood.models.Product;
import com.example.polyfood.models.ProductImage;
import com.example.polyfood.models.ProductReview;
import com.example.polyfood.models.responobj.Respon;
import com.example.polyfood.repository.IOrderDetailRepository;
import com.example.polyfood.repository.IProductImageRepository;
import com.example.polyfood.repository.IProductRepository;
import com.example.polyfood.repository.IProductReviewRepository;
import com.example.polyfood.services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices implements IProductServices {
    @Autowired
    IProductRepository productRepository;
    @Autowired
    IProductReviewRepository productReviewRepository;
    @Autowired
    IProductImageRepository productImageRepository;
    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Override
    public Respon<Product> creatProduct(Product product) {
        Respon respon = new Respon();
        Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
        if(optionalProduct.isEmpty())
        {
            Date date = new Date();
            product.setCreateAt(date);
            product.setUpdateAt(date);
            productRepository.save(product);
            respon.setStatus(200);
            respon.setMassage("Thêm mới thành công!");
            respon.setData(product);
        }
        else
        {
            respon.setStatus(404);
            respon.setMassage("Thêm mới thất bại do id sản phẩm đã tồn tại");
        }
        return respon;
    }

    @Override
    public Respon<Product> updatePrduct(Product product) {
        Respon respon = new Respon();
        Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
        if (!optionalProduct.isEmpty()) {
            Product product1 = productRepository.getReferenceById(product.getProductId());
            product.setCreateAt(product1.getCreateAt());
            Date date = new Date();
            product.setUpdateAt(date);
            productRepository.save(product);
            respon.setStatus(200);
            respon.setMassage("Sửa thành công");
            respon.setData(product);
        } else {
            respon.setStatus(404);
            respon.setMassage("Không tìm thấy id sản phẩm");
        }
        return respon;
    }

    @Override
    public Respon<Product> delete(int idProduct) {
        Respon respon = new Respon();
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if (!optionalProduct.isEmpty()) {
            for (OrderDetail element : orderDetailRepository.findAll()) {
                if (element.getProduct().getProductId() == idProduct)
                    orderDetailRepository.delete(element);
            }
            for (ProductReview element : productReviewRepository.findAll()) {
                if (element.getProduct().getProductId() == idProduct)
                    productReviewRepository.delete(element);
            }
            for (ProductImage element : productImageRepository.findAll()) {
                if (element.getProduct().getProductId() == idProduct)
                    productImageRepository.delete(element);
            }
            respon.setMassage("Xoá thành công!");
            respon.setStatus(200);
            respon.setData(productRepository.findById(idProduct));
            productRepository.delete(productRepository.findById(idProduct).get());
        }
        else
        {
            respon.setStatus(404);
            respon.setMassage("Không tìm thấy sản phẩm");
        }
            return respon;
    }

    @Override
    public Page<Product> seachProduct(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return productRepository.findAllByNameProductContains(name,pageable);
    }

    @Override
    public Page<Product> seachByPrice(int value1, int value2, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return productRepository.findAllByPriceBetween(value1,value2,pageable);
    }

    @Override
    public Page<Product> getAllProduct(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return productRepository.findAll(pageable);
    }
}
