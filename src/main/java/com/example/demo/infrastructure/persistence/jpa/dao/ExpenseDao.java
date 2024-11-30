package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.infrastructure.persistence.jpa.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseDao extends JpaRepository<ExpenseEntity, String> {
    List<ExpenseEntity> findByPaymentMethodId(String paymentMethodId);
}
