package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.infrastructure.persistence.jpa.entity.RefuelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefuelDao extends JpaRepository<RefuelEntity, String> {
}
