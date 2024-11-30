package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Category;
import com.example.demo.infrastructure.persistence.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toDomain(CategoryEntity categoryEntity);
}
