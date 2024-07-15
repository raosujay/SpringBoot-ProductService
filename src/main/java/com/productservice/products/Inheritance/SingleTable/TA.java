package com.productservice.products.Inheritance.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("3")
@Entity
public class TA extends User {
    public  int noOfSession;
    private float avgRating;
}