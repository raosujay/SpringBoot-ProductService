package com.productservice.products.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
//Entities represent tables in a relational database
//Each instance of an entity corresponds to a row in that table.
@Getter
@Setter
public class Product extends BaseModel {
    private float price;
    private String description;
    private String image;

    //Now we are assuming - one category is having many products
    @ManyToOne
    private Category category;
}