package com.example.demo.application.service;

import com.example.demo.api.dto.CategoryDto;

import com.example.demo.application.mapper.CategoryMapper;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService{

    private final CategoryRepository categoryRepository;

    public String createCategory(Category category){
        return categoryRepository.insert(category);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
}
