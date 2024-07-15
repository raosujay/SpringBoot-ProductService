package com.productservice.products.Inheritance.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("2")
@Entity
public class Mentor extends User {
    private String company;
    private float avgRating;
}