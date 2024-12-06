package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.domain.model.Bill;
import com.example.demo.infrastructure.persistence.jpa.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDao extends JpaRepository<BillEntity, String> {
}
