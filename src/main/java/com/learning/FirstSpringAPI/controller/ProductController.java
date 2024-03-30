package com.learning.FirstSpringAPI.controller;

import com.learning.FirstSpringAPI.models.Product;
import com.learning.FirstSpringAPI.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController //This controller is going to host REST HTTP API's
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*@GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return  productService.getProductById(id);
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        Product product=productService.getProductById(id);
        if(product==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return  productService.replaceProduct(id,product);
    }
    @PostMapping()
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    //create product
    //delete product
    //update product -> partial Update (PATCH)
    //replace product -> Replace (PUT)
}
