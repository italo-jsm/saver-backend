package com.example.demo.api.controller;

import com.example.demo.api.dto.ProductDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.application.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.created(URI.create(productService.createProduct(productDto))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id){
        return ResponseEntity.ok(productService
                .getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found", id, ProductDto.class.getName()))
        );
    }
}
