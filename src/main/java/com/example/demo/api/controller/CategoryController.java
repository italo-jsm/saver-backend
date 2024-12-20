package com.example.demo.api.controller;

import com.example.demo.api.dto.CategoryDto;
import com.example.demo.application.mapper.CategoryMapper;
import com.example.demo.application.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.created(URI.create(categoryService.createCategory(categoryMapper.toDomain(categoryDto)))).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() throws FileNotFoundException {
        return ResponseEntity.ok(categoryService.getAll().stream().map(categoryMapper::toDto).toList());
    }

}
