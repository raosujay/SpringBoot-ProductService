package com.productservice.products.ControllerAdvice;

import com.productservice.products.Dtos.ProductResponseSelf;
import com.productservice.products.Exceptions.ProductNotPresentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//This is a general and global exception handler
@RestControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotPresentException.class)
    public ResponseEntity<ProductResponseSelf> handleInvalidProduct() {
        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Product doesn't exist - Controller Advice");
        return new ResponseEntity<>(productResponseSelf, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ProductResponseSelf> handleArithematicException() {
        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Something Went Wrong - Controller Advice");
        return new ResponseEntity<>(productResponseSelf, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}