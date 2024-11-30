package com.example.demo.application.mapper;

import com.example.demo.api.dto.ProductDto;
import com.example.demo.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toDomain(ProductDto productDto);
}
