package com.example.shop.dao;

import com.example.shop.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductCategoryDAO extends JpaRepository<ProductCategoryEntity, Long> {

    List<ProductCategoryEntity> findByName(String name);


}
