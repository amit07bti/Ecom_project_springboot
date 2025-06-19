package com.pearsystem.controller;

import com.pearsystem.model.Product;
import com.pearsystem.payload.ProductDto;
import com.pearsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
     // CREATE PRODUCT
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product){
       ProductDto createProduct= productService.createProduct(product);
       return new ResponseEntity<ProductDto>(createProduct, HttpStatus.CREATED);

    }
    // GET ALL PRODUCTS
    @GetMapping("/get_products")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        List<ProductDto> viewProduct= productService.getAllProducts();
        return new ResponseEntity<>(viewProduct, HttpStatus.OK);


    }
    // GET PRODUCT BY ID
    @GetMapping("/get_products/{productId}")
    public ResponseEntity<ProductDto >viewProductById(@PathVariable int productId){
        ProductDto viewProductById =productService.getProductById(productId);
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
    public  ResponseEntity<ProductDto> updateProduct(@PathVariable int productId,@RequestBody  ProductDto newproduct){
      ProductDto updateProduct=  productService.updateProduct(productId,newproduct);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
}
