package com.pearsystem.repository;

import com.pearsystem.model.Category;
import com.pearsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);


}
