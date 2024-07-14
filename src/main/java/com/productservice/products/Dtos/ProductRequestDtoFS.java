package com.productservice.products.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDtoFS {
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
}