package com.pearsystem.controller;

import com.pearsystem.model.Product;
import com.pearsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Product createProduct( @RequestBody Product product){
       Product createProduct= productService.createProduct(product);

        return  createProduct;

    }
    // GET ALL PRODUCTS
    @GetMapping("/get_products")
    public Product getAllProduct(){
        return productService.getAllProducts();


    }
    // GET PRODUCT BY ID
    @GetMapping("/get_products/{productId}")
    public Product viewProductById(@PathVariable int productId){
        Product viewProductById =productService.getProductById(productId);
        return viewProductById;
    }
    //DELETE PRODUCT
    @DeleteMapping("/del/{productId}")
    public String deleteProductById(@PathVariable  int productId){
        productService.deleteProduct(productId);
        return "Successfully deleted product!  ";
    }
    //UPDATE PRODUCT
    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable int productId,@ResponseBody Product newproduct){
      Product updateProduct=  productService.updateProduct(productId,newproduct);
        return updateProduct;
    }
}
