package com.learning.FirstSpringAPI.services;


import com.learning.FirstSpringAPI.dtos.FakeStoreProductDTO;
import com.learning.FirstSpringAPI.models.Category;
import com.learning.FirstSpringAPI.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    public List<Product> getAllProduct() {
        FakeStoreProductDTO[] fakeStoreProductDTO=
                restTemplate.getForObject("https://fakestoreapi.com/products",FakeStoreProductDTO[].class);

        //converting the list of fakestoredto into list of Prodtuct
        List<Product> responseProduct=new ArrayList<>();

        if(fakeStoreProductDTO==null){
            return null;
        }

        for(FakeStoreProductDTO fakeStoreProductDTO1 : fakeStoreProductDTO){
            responseProduct.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDTO1));
        }

        return responseProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setImage(product.getImage());
        fakeStoreProductDTO.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDTO response=
                restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(response);
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();

        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImage());
        fakeStoreProductDTO.setCategory(product.getCategory().getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class,
                restTemplate.getMessageConverters());

        FakeStoreProductDTO response=
                restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.POST, requestCallback, responseExtractor);


        return convertFakeStoreProductDtoToProduct(response);
    }
}
