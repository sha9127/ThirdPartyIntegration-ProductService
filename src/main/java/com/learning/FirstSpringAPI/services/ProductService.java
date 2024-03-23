package com.learning.FirstSpringAPI.services;

import com.learning.FirstSpringAPI.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProduct(Long id);
}
