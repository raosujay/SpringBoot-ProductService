package com.productservice.products.Controllers;

import com.productservice.products.Dtos.ProductRequestDtoFS;
import com.productservice.products.Dtos.ProductResponseSelf;
import com.productservice.products.Exceptions.ProductNotPresentException;
import com.productservice.products.Models.Category;
import com.productservice.products.Models.Product;
import com.productservice.products.Repositories.ProductRepository;
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

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        //to get queries from mySql we have to implement in product repository
        //productRepository.findByDescription();
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

    //Here we are returning with ProductResponseSelf Dto & using old try nd catch block
    @GetMapping("/product/{id}")
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

    //Here we are returning with ProductResponseSelf Dto & ExceptionHandler
    @GetMapping("/product/exception/{id}")
    public ResponseEntity<ProductResponseSelf> getSingleProductException(@PathVariable("id") Long id)
            throws ProductNotPresentException {
        Product product = productService.getSingleProduct(id);
        return new ResponseEntity<>(new ProductResponseSelf(product,"Success"), HttpStatus.OK);
    }

//    Using exception handler to catch the errors and throw exceptions
//    We are moving this to ProductControllerAdvice as we want to handle this globally & keep controller clean
//    If we write exception here it'll be having more preference over ControllerAdvice
//    @ExceptionHandler(ProductNotPresentException.class)
//    public ResponseEntity<ProductResponseSelf> handleInvalidProduct() {
//        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Product doesn't exist");
//        return new ResponseEntity<>(productResponseSelf, HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(ArithmeticException.class)
//    public ResponseEntity<ProductResponseSelf> handleArithematicException() {
//        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Something Went Wrong");
//        return new ResponseEntity<>(productResponseSelf, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @GetMapping("/products/categories")
    public List<Category> getAllCategories() {

        return new ArrayList<>();
    }

    @GetMapping("/products/search")
    public Product getProductsByName(@RequestParam("name") String name) {
        return productRepository.findByName(name);
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