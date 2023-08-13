package com.example.polyfood.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Product_Image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private int product_Image_Id;

    @Column(name = "title")
    private String title;

    @Column(name = "image_product")
    private String imageProduct;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private Date createAt;

    @Column(name = "updated_at")
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false, referencedColumnName = "product_id")
    @JsonBackReference
    private Product product;

    public int getProduct_Image_Id() {
        return product_Image_Id;
    }

    public void setProduct_Image_Id(int product_Image_Id) {
        this.product_Image_Id = product_Image_Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
