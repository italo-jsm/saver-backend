package com.example.demo.domain.repository;

import com.example.demo.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> findAll();
    String insert(Category category);
    Optional<Category> findByName(String name);
}
