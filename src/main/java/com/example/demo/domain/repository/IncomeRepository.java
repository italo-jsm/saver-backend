package com.example.demo.domain.repository;

import com.example.demo.domain.model.Income;

import java.util.List;

public interface IncomeRepository {
    String insert(Income income);

    List<Income> findAllIncomes(int month, int year);

}
