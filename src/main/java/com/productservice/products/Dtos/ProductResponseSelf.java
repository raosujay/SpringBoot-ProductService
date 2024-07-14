package com.productservice.products.Dtos;

import com.productservice.products.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponseSelf {
    private Product product;
    private String message;
}
