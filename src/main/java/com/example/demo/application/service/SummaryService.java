package com.example.demo.application.service;

import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Income;
import com.example.demo.domain.model.Summary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final IncomeService incomeService;
    private final BillService billService;

    public List<Summary> getSummary(LocalDate begin, LocalDate end){
        List<Summary> summaryList = new ArrayList<>();
        while(begin.isBefore(end)){
            List<Income> incomes = incomeService.getIncomesByMonthAndYear(begin.getMonth().getValue(), begin.getYear());
            List<Bill> outcomes = billService.getBillsByMonthAndYear(begin.getMonth().getValue(), begin.getYear());
            summaryList.add(
                    Summary.builder()
                    .totalOutcomes(outcomes.stream().map(Bill::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                    .totalIncomes(incomes.stream().map(Income::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                    .month(begin.getMonth().getValue())
                    .year(begin.getYear())
                    .build()
            );
            begin = begin.plusMonths(1);
        }
        return summaryList;
    }
}
