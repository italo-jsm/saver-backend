package com.example.demo.domain.repository;

import com.example.demo.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {
    String insert(Product product);
    Optional<Product> findProductById(String id);
}
