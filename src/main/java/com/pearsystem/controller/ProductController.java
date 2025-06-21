package com.pearsystem.controller;

import com.pearsystem.model.Product;
import com.pearsystem.payload.AppConstants;
import com.pearsystem.payload.ProductDto;
import com.pearsystem.payload.ProductResponse;
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
    @PostMapping("/create/{catid}")

    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product,@PathVariable int catid){
       ProductDto createProduct= productService.createProduct(product,catid);
       return new ResponseEntity<ProductDto>(createProduct, HttpStatus.CREATED);

    }
    // GET ALL PRODUCTS
    @GetMapping("/get_all_products")
    public ProductResponse getAllProduct(@RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER_STRING,required = false) int pageNumber,
                                                          @RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE_STRING,required = false)int pageSize,
                                                          @RequestParam (value = "sortBy",defaultValue = AppConstants.SHORT_BY_STRING,required = false)String sortBy,
                                                          @RequestParam (value = "sortDir",defaultValue = AppConstants.SHORT_DIRECTION_STRING,required = false) String sortDir){
        ProductResponse response = productService.getAllProducts(pageNumber,pageSize,sortBy,sortDir);
        return response;


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
