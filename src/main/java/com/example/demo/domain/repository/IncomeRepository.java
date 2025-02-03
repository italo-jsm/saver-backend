package com.example.demo.domain.repository;

import com.example.demo.domain.model.Income;

import java.util.List;

public interface IncomeRepository {
    String insert(Income income);

    List<Income> findByPaymentMonthAndYear(int month, int year);
}
