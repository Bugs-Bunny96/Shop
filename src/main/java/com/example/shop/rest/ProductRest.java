package com.example.shop.rest;

import com.example.shop.entity.dto.ProductDto;
import com.example.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/productCategory/product")
public class ProductRest {

    private final ProductService productService;

    @PostMapping("/add/{id}")
    public ResponseEntity<Object> addNewProduct(@PathVariable Long id, @RequestBody ProductDto productDto ){
        try {
            productService.add(id, productDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byCategory")
    public ResponseEntity<Object> getAllByCategoryProducts(@RequestParam(required = true) String name) {
        try {
            return new ResponseEntity<>(productService.findByCategory(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll(@RequestParam(required = false) Long limit){
        try {
            return new ResponseEntity<>(productService.getAll(limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
