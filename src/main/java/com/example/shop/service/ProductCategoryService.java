package com.example.shop.service;

import com.example.shop.dao.ProductCategoryDAO;
import com.example.shop.entity.ProductCategoryEntity;
import com.example.shop.entity.dto.ProductCategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryDAO productCategoryDAO;

    public void add(ProductCategoryDto productCategoryDto) {
        ProductCategoryEntity productCategory = new ProductCategoryEntity();
        productCategory.setName(productCategoryDto.getName());
        productCategoryDAO.save(productCategory);
    }

    public List<ProductCategoryDto> getAll(Long limit) {
        List<ProductCategoryDto> productCategories = new ArrayList<>();
        List<ProductCategoryEntity> daoProductCategories = productCategoryDAO.findAll();
        if (limit != null) {
            for (int i=0; i<limit; i++) {
                ProductCategoryDto productCategory = new ProductCategoryDto();
                productCategory.setId(daoProductCategories.get(i).getId());
                productCategory.setName(daoProductCategories.get(i).getName());
                productCategories.add(productCategory);
                if (i == daoProductCategories.size()-1) {
                    break;
                }
            }
        } else {
            for (ProductCategoryEntity daoProductCategory : daoProductCategories) {
                ProductCategoryDto productCategory = new ProductCategoryDto();
                productCategory.setId(daoProductCategory.getId());
                productCategory.setName(daoProductCategory.getName());
                productCategories.add(productCategory);
            }
        }
        return productCategories;
    }

    public void delete(Long id) {
        productCategoryDAO.deleteById(id);
    }

    public void update(Long id, ProductCategoryDto productCategoryDto) {
        ProductCategoryEntity productCategory = productCategoryDAO.getById(id);
        productCategory.setName(productCategoryDto.getName());
        productCategoryDAO.save(productCategory);
    }

}
