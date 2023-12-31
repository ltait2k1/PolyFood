package com.example.polyfood.repository;

import com.example.polyfood.models.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductTypeRepository extends JpaRepository<ProductType,Integer> {
    Page<ProductType> findAllByNameProuctTypeContains(String name, Pageable pageable);
}
