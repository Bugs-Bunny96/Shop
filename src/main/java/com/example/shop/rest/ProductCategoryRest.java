package com.example.shop.rest;

import com.example.shop.entity.dto.ProductCategoryDto;
import com.example.shop.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/productCategory")
public class ProductCategoryRest {

    private final ProductCategoryService productCategoryService;

    @PostMapping("/add")
    public ResponseEntity<Object> addNewProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        try {
            productCategoryService.add(productCategoryDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllProductCategories(@RequestParam(required = false) Long limit) {
        try {
            return new ResponseEntity<>(productCategoryService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProductCategory(@PathVariable Long id){
        try {
            productCategoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDto productCategoryDto){
        try {
            productCategoryService.update(id, productCategoryDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
