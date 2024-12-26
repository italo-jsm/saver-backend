package com.example.demo.api.controller;

import com.example.demo.api.dto.CategoryDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.mapper.CategoryMapper;
import com.example.demo.application.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.net.URI;

import java.util.List;

import static com.example.demo.api.dto.response.CreatedResponse.RESOURCE_ID;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CreatedResponse> saveCategory(@RequestBody CategoryDto categoryDto){
        String categoryId = categoryService.createCategory(categoryMapper.toDomain(categoryDto));
        return ResponseEntity.created(URI.create(categoryId)).body(new CreatedResponse(RESOURCE_ID, categoryId));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() throws FileNotFoundException {
        return ResponseEntity.ok(categoryService.getAll().stream().map(categoryMapper::toDto).toList());
    }

}
