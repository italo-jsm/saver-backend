package com.example.demo.application.mapper;

import com.example.demo.api.dto.CategoryDto;
import com.example.demo.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toDomain(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
}
