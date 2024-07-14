package com.productservice.products.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDtoFS {
    private Long id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
}