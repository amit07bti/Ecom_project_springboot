package com.pearsystem.controller;

import com.pearsystem.model.Product;
import com.pearsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
     // CREATE PRODUCT
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
       Product createProduct= productService.createProduct(product);
       return new ResponseEntity<Product>(createProduct, HttpStatus.CREATED);

    }
    // GET ALL PRODUCTS
    @GetMapping("/get_products")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> viewProduct= productService.getAllProducts();
        return new ResponseEntity<>(viewProduct, HttpStatus.OK);


    }
    // GET PRODUCT BY ID
    @GetMapping("/get_products/{productId}")
    public ResponseEntity<Product >viewProductById(@PathVariable int productId){
        Product viewProductById =productService.getProductById(productId);
        return new ResponseEntity<>(viewProductById, HttpStatus.OK);
    }
    //DELETE PRODUCT
    @DeleteMapping("/del/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable  int productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Successfully deleted product! ",HttpStatus.OK);

    }
    //UPDATE PRODUCT
    @PutMapping("/update/{productId}")
    public  ResponseEntity<Product> updateProduct(@PathVariable int productId,@RequestBody  Product newproduct){
      Product updateProduct=  productService.updateProduct(productId,newproduct);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
}
