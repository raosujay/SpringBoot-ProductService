package com.productservice.products.Inheritance.MpSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "mp_ta")
public class TA extends User{
    public  int noOfSession;
    private float avgRating;
}