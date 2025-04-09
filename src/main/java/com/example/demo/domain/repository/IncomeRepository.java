package com.example.demo.domain.repository;

import com.example.demo.domain.model.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository {
    String insert(Income income);

    List<Income> findAllIncomes(int month, int year);

    List<Income> findIncomesByDate(LocalDate date);

}
