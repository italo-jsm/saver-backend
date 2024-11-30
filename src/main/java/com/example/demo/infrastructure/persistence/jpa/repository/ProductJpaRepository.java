package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Product;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.ProductDao;
import com.example.demo.infrastructure.persistence.jpa.mapper.ProductEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@AllArgsConstructor
@Repository
public class ProductJpaRepository implements ProductRepository {

    private final ProductDao productDao;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public String insert(Product product) {
        return productDao.save(productEntityMapper.toEntity(product)).getSku();
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return productDao
                .findById(id)
                .map(productEntityMapper::toDomain);
    }

}
