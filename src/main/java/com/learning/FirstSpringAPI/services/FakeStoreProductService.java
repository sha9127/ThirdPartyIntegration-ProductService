package com.learning.FirstSpringAPI.services;


import com.learning.FirstSpringAPI.dtos.FakeStoreProductDTO;
import com.learning.FirstSpringAPI.models.Category;
import com.learning.FirstSpringAPI.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDTO fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        category.setId(fakeStoreProductDto.getId());

        product.setCategory(category);

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        //Call the FakeStore API to get the product with given ID here.
        FakeStoreProductDTO fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);

        if (fakeStoreProductDto == null) {
            return null;
        }

        //Convert fakeStoreProductDto to product object.
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }

    @Override
    public List<Product> getAllProduct(Long id) {
        return null;
    }
}
