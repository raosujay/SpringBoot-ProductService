package com.productservice.products.Inheritance.MpSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "mp_instructor")
public class Instructor extends User{
    private String specialization;
}
