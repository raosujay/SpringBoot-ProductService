package com.productservice.products.Inheritance.MpSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "mp_mentor")
public class Mentor extends User{
    private String company;
    private float avgRating;
}
