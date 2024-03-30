package com.learning.FirstSpringAPI.services;

import com.learning.FirstSpringAPI.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProduct();
    Product replaceProduct(Long id,Product product);
    Product addProduct(Product product);
}
