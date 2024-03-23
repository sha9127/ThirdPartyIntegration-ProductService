package com.learning.FirstSpringAPI.controller;

import com.learning.FirstSpringAPI.models.Product;
import com.learning.FirstSpringAPI.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController //This controller is going to host REST HTTP API's
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return  productService.getProductById(id);
    }
    @GetMapping()
    public List<Product> getAllProducts(){
        return new ArrayList<>();
    }

    //create product
    //delete product
    //update product -> partial Update (PATCH)
    //replace product -> Replace (PUT)
}
