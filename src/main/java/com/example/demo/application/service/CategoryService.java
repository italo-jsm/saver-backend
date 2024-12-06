package com.example.demo.application.service;

import com.example.demo.api.dto.CategoryDto;

import com.example.demo.application.mapper.CategoryMapper;
import com.example.demo.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public String createCategory(CategoryDto categoryDto){
        return categoryRepository.insert(categoryMapper.toDomain(categoryDto));
    }

    public List<CategoryDto> getAll(){
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }
}
