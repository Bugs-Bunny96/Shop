package com.example.shop.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class ProductEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    private ProductCategoryEntity productCategory;

}
