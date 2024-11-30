package com.example.demo.application.service;

import com.example.demo.api.dto.ProductDto;
import com.example.demo.application.mapper.ProductMapper;
import com.example.demo.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productJpaRepository;
    private final ProductMapper productMapper;

    public String createProduct(ProductDto productDto){
        return productJpaRepository.insert(productMapper.toDomain(productDto));
    }

    public Optional<ProductDto> getProductById(String id) {
        return productJpaRepository.findProductById(id).map(productMapper::toDto);
    }
}
