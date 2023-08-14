package com.example.polyfood.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.Date;

@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id")
    private int productReviewId;

    @Column(name = "content_rated")
    private String contentRated;

    @Column(name = "point_evaluation")
    private int pointEvaluation;

    @Column(name = "content_seen")
    private String contentSeen;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false, referencedColumnName = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false, referencedColumnName = "user_id")
    @JsonBackReference
    private User user;

    public int getProductReviewId() {
        return productReviewId;
    }

    public void setProductReviewId(int productReviewId) {
        this.productReviewId = productReviewId;
    }

    public String getContentRated() {
        return contentRated;
    }

    public void setContentRated(String contentRated) {
        this.contentRated = contentRated;
    }

    public int getPointEvaluation() {
        return pointEvaluation;
    }

    public void setPointEvaluation(int pointEvaluation) {
        this.pointEvaluation = pointEvaluation;
    }

    public String getContentSeen() {
        return contentSeen;
    }

    public void setContentSeen(String contentSeen) {
        this.contentSeen = contentSeen;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
