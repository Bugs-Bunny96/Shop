package com.example.shop.dao;

import com.example.shop.entity.ProductCategoryEntity;
import com.example.shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByProductCategory(ProductCategoryEntity productCategory);


}
