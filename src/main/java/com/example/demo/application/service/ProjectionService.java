package com.example.demo.application.service;

import com.example.demo.api.dto.BillDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProjectionService {

    private final BillService billService;

    public BigDecimal getProjectionByDate(LocalDate projectionDate, BigDecimal currentAccountBalance){
        BigDecimal totalExpenseProjection = billService.findByDueDateBetween(LocalDate.now(), projectionDate).stream().map(BillDto::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        BigDecimal totalIncomeProjection = billService.findByDueDateBetween(LocalDate.now(), projectionDate).stream().map(BillDto::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        return totalIncomeProjection.subtract(totalExpenseProjection).add(currentAccountBalance);
    }
}
