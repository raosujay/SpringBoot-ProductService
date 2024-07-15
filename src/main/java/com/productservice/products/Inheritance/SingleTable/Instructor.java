package com.productservice.products.Inheritance.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("1")
@Entity
public class Instructor extends User {
    private String specialization;
}