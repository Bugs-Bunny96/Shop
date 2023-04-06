package com.example.shop.service;

import com.example.shop.dao.ProductCategoryDAO;
import com.example.shop.dao.ProductDAO;
import com.example.shop.entity.ProductCategoryEntity;
import com.example.shop.entity.ProductEntity;
import com.example.shop.entity.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductDAO productDao;
    private final ProductCategoryDAO productCategoryDAO;

    public void add(Long id, ProductDto productDto){
        ProductCategoryEntity productCategoryEntity = productCategoryDAO.findById(id).get();
        ProductEntity product = new ProductEntity();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setProductCategory(productCategoryEntity);
        productDao.save(product);

    }

    public List<Long> findByCategory(String name){
        List<ProductCategoryEntity> productCategoryList = productCategoryDAO.findByName(name);
        List<Long> productList = new ArrayList<>();
        productCategoryList.forEach(productCategory -> productDao
                .findAllByProductCategory(productCategory)
                .forEach(product -> productList.add(product.getId())));
        return productList;
    }


    public List<ProductDto> getAll(Long limit) {
        List<ProductDto> products = new ArrayList<>();
        List<ProductEntity> daoProducts = productDao.findAll();
        if (limit != null) {
            for (int i=0; i<limit; i++) {
                ProductDto product = new ProductDto();
                product.setId(daoProducts.get(i).getId());
                product.setName(daoProducts.get(i).getName());
                product.setDescription(daoProducts.get(i).getDescription());
                product.setPrice((daoProducts.get(i).getPrice()));
                products.add(product);
                if (i == daoProducts.size()-1) {
                    break;
                }
            }
        } else {
            for (ProductEntity daoProduct : daoProducts) {
                ProductDto product = new ProductDto();
                product.setId(daoProduct.getId());
                product.setName(daoProduct.getName());
                product.setDescription(daoProduct.getDescription());
                product.setPrice((daoProduct.getPrice()));
                products.add(product);
            }
        }
        return products;
    }

}
