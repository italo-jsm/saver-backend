package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Category;
import com.example.demo.domain.repository.CategoryRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.CategoryDao;
import com.example.demo.infrastructure.persistence.jpa.mapper.CategoryEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class CategoryJpaRepository implements CategoryRepository {

    private final CategoryDao categoryDao;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll().stream().map(categoryEntityMapper::toDomain).toList();
    }

    @Override
    public String insert(Category category) {
        return categoryDao.save(categoryEntityMapper.toEntity(category)).getId();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryDao.findByName(name).map(categoryEntityMapper::toDomain);
    }

}
