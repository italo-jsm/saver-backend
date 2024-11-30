package com.example.demo.domain.repository;

import com.example.demo.domain.model.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    String insert(Expense expense);
    Optional<Expense> findById(String id);
    List<Expense> findByPaymentMethodId(String paymentMethodId);
}
