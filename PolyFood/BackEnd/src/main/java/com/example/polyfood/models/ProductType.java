package com.example.polyfood.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type_id")
    private int productTypeId;

    @Column(name = "name_product_type")
    private String nameProuctType;

    @Column(name = "image_type_product")
    private String imageTypeProduct;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updateAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productType", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Product> products;

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getNameProuctType() {
        return nameProuctType;
    }

    public void setNameProuctType(String nameProuctType) {
        this.nameProuctType = nameProuctType;
    }

    public String getImageTypeProduct() {
        return imageTypeProduct;
    }

    public void setImageTypeProduct(String imageTypeProduct) {
        this.imageTypeProduct = imageTypeProduct;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
