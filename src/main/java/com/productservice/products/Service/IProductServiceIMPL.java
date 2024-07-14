package com.productservice.products.Service;

import com.productservice.products.Dtos.ProductResponseDtoFS;
import com.productservice.products.Exceptions.ProductNotPresentException;
import com.productservice.products.Models.Category;
import com.productservice.products.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//This is an implementation of IProductService
@Service
public class IProductServiceIMPL implements IProductService{
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getSingleProduct(Long id) throws ProductNotPresentException {

        //RestTemplate restTemplate = new RestTemplate();
        //Instead of creating RestTemplate again, and again, we have made it autowired
        //by making a separate package as RestTemplate is accessing from Library, and it's not a component
        if (id > 20 && id <= 40) {
            throw new ProductNotPresentException();
        }
        if (id > 40) {
            throw new ArithmeticException();
        }
        ProductResponseDtoFS response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                ProductResponseDtoFS.class);

        return getProductFromResponseDto(response);
    }

    //Get all Products from FakeStoreAPI
    @Override
    public List<Product> getAllProducts() {
        //Instead of List<ProductResponseDto>.class we used List.class due to TypeErasure
        //restTemplate.getForObject("https://fakestoreapi.com/products", List.class);
        ProductResponseDtoFS[] products = restTemplate.getForObject("https://fakestoreapi.com/products",
                ProductResponseDtoFS[].class);

        List<Product> output = new ArrayList<>();
        for (ProductResponseDtoFS productResponseDtoFS : products) {
            output.add(getProductFromResponseDto(productResponseDtoFS));
        }
        return output;
    }

    private Product getProductFromResponseDto(ProductResponseDtoFS response) {
        Product product = new Product();

        product.setId(response.getId());
        product.setName(response.getTitle());
        product.setDescription(response.getDescription());
        product.setPrice(response.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(response.getCategory());
        product.setImage(response.getImage());

        return product;
    }
}