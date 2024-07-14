package com.productservice.products.Controllers;

import com.productservice.products.Dtos.ProductRequestDtoFS;
import com.productservice.products.Dtos.ProductResponseSelf;
import com.productservice.products.Exceptions.ProductNotPresentException;
import com.productservice.products.Models.Category;
import com.productservice.products.Models.Product;
import com.productservice.products.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    //what is this tag - find out
    @Autowired
    IProductService productService;
    //suppose we have to connect to DB from fakeStore/other service then we don't have to change in the code
    //we can change in the IProductService Interface

    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

//    Here we are sending a product response
//    @GetMapping("/products/{id}")
//    public ResponseEntity<ProductResponseSelf> getSingleProduct(@PathVariable("id") Long id) {
//        Product product;
//        try {
//            product = productService.getSingleProduct(id);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }

    //Here we are returning with ProductResponseSelf Dto
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseSelf> getSingleProduct(@PathVariable("id") Long id) {
        Product product;
        try {
            product = productService.getSingleProduct(id);
        } catch (ProductNotPresentException e) {
            ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Product doesn't exist");
            return new ResponseEntity<>(productResponseSelf, HttpStatus.NOT_FOUND);
        } catch (ArithmeticException e) {
            ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Something went wrong");
            return new ResponseEntity<>(productResponseSelf, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ProductResponseSelf(product,"Success"), HttpStatus.OK);
    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories() {

        return new ArrayList<>();
    }

    @GetMapping("/products/categories/{id}")
    public List<Product> getAllProductsInCategory(@PathVariable("id") Long id) {

        return new ArrayList<>();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDtoFS requestDto) {
        return new Product();
    }

    @PatchMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id,
                                 @RequestBody ProductRequestDtoFS requestDto) {
        return new Product();
    }
    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id,
                                 @RequestBody ProductRequestDtoFS requestDto) {
        return true;
    }
 }