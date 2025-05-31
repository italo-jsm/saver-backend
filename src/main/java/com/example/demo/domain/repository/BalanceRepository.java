package com.example.demo.domain.repository;

import com.example.demo.domain.model.Balance;

import java.time.LocalDate;

public interface BalanceRepository {
    void createBalance(Balance balance);
    Balance findByBalanceDate(LocalDate date);
    Balance findFirst();
    Balance findLast();
}
