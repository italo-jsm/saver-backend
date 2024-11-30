package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.infrastructure.persistence.jpa.entity.BatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchDao extends JpaRepository<BatchEntity, String> {
}
