package com.example.demo.domain.repository;

import com.example.demo.domain.model.Expense;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    String insert(Expense expense);
    void delete(Expense expense);
    Optional<Expense> findById(String id);
    List<Expense> findByPaymentMethodId(String paymentMethodId);
    List<Expense> findByPaymentMonthAndYear(int month, int year);
    List<Expense> findAll();
    List<Expense> findInvoiceExpenses(String paymentMethodId, YearMonth reference);
}
