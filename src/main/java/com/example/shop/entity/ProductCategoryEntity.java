package com.example.shop.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductCategoryEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

}
