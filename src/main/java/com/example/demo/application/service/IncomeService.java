package com.example.demo.application.service;

import com.example.demo.domain.model.Income;
import com.example.demo.domain.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public String saveIncome(Income income){
        return incomeRepository.insert(income);
    }


    public List<Income> getIncomesByMonthAndYear(int month, int year){
        return incomeRepository.findByPaymentMonthAndYear(month, year);
    }
}
