package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Product;
import com.example.demo.infrastructure.persistence.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    Product toDomain(ProductEntity productEntity);
    ProductEntity toEntity(Product product);
}
