package com.pearsystem.service;

import com.pearsystem.model.Product;
import com.pearsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
      Product saved=  productRepository.save(product);

        return saved;
    }

    public List<Product> getAllProducts(){
       return productRepository.findAll();

    }
    public Product getProductById(int pid){
        Product viewById= productRepository.findById(pid).orElseThrow(() -> new RuntimeException(pid+"   Product not found   "));
        return viewById;

    }
    public void deleteProduct(int pid){
        Product byId=productRepository.findById(pid).orElseThrow(() -> new RuntimeException(pid+"   Product not found   "));
        productRepository.delete(byId);

    }
    public Product updateProduct(int pid,Product newProduct){
        Product oldProduct= productRepository.findById(pid).orElseThrow(()-> new RuntimeException(pid+"  Product not found  "));

        oldProduct.setProduct_name(newProduct.getProduct_name());
        oldProduct.setProduct_description(newProduct.getProduct_description());
        oldProduct.setProduct_price(newProduct.getProduct_price());
        oldProduct.setProduct_quantity(newProduct.getProduct_quantity());
        oldProduct.setStock(newProduct.isStock());
        oldProduct.setLive(newProduct.isLive());
        oldProduct.setProduct_imageName(newProduct.getProduct_imageName());
       Product save= productRepository.save(oldProduct);
        return save;
        




    }

}
