package com.example.demo.application.service;

import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Income;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SummaryServiceTest {

    @InjectMocks
    private BalanceService summaryService;

    @Mock
    private BillService billService;

    @Mock
    private IncomeService incomeService;

    @Test
    public void lala(){
        Mockito.when(billService.getBillsByMonthAndYear(1, 2025)).thenReturn(List.of(
                Bill.builder()
                        .amount(BigDecimal.valueOf(100))
                        .dueDate(LocalDate.of(2025, 1, 10))
                        .build(),
                Bill.builder()
                        .amount(BigDecimal.valueOf(110))
                        .dueDate(LocalDate.of(2025, 1, 10))
                        .build(),
                Bill.builder()
                        .amount(BigDecimal.valueOf(120))
                        .dueDate(LocalDate.of(2025, 1, 10))
                        .build()
                ));

        Mockito.when(billService.getBillsByMonthAndYear(2, 2025)).thenReturn(List.of(
                Bill.builder()
                        .amount(BigDecimal.valueOf(200))
                        .dueDate(LocalDate.of(2025, 2, 10))
                        .build(),
                Bill.builder()
                        .amount(BigDecimal.valueOf(210))
                        .dueDate(LocalDate.of(2025, 2, 10))
                        .build(),
                Bill.builder()
                        .amount(BigDecimal.valueOf(220))
                        .dueDate(LocalDate.of(2025, 2, 10))
                        .build()
        ));

        Mockito.when(billService.getBillsByMonthAndYear(3, 2025)).thenReturn(List.of(
                Bill.builder()
                        .amount(BigDecimal.valueOf(300))
                        .dueDate(LocalDate.of(2025, 3, 10))
                        .build(),
                Bill.builder()
                        .amount(BigDecimal.valueOf(310))
                        .dueDate(LocalDate.of(2025, 3, 10))
                        .build(),
                Bill.builder()
                        .amount(BigDecimal.valueOf(320))
                        .dueDate(LocalDate.of(2025, 3, 10))
                        .build()
        ));

        Mockito.when(incomeService.getIncomesByMonthAndYear(1, 2025)).thenReturn(List.of(
                Income.builder()
                        .amount(BigDecimal.valueOf(1000))
                        .dueDate(LocalDate.of(2025, 1, 10))
                        .build(),

                Income.builder()
                        .amount(BigDecimal.valueOf(1100))
                        .dueDate(LocalDate.of(2025, 1, 10))
                        .build(),

                Income.builder()
                        .amount(BigDecimal.valueOf(1200))
                        .dueDate(LocalDate.of(2025, 1, 10))
                        .build()
        ));

        Mockito.when(incomeService.getIncomesByMonthAndYear(2, 2025)).thenReturn(List.of(
                Income.builder()
                        .amount(BigDecimal.valueOf(2000))
                        .dueDate(LocalDate.of(2025, 2, 10))
                        .build(),

                Income.builder()
                        .amount(BigDecimal.valueOf(2100))
                        .dueDate(LocalDate.of(2025, 2, 10))
                        .build(),

                Income.builder()
                        .amount(BigDecimal.valueOf(2200))
                        .dueDate(LocalDate.of(2025, 2, 10))
                        .build()
        ));

        Mockito.when(incomeService.getIncomesByMonthAndYear(3, 2025)).thenReturn(List.of(
                Income.builder()
                        .amount(BigDecimal.valueOf(3000))
                        .dueDate(LocalDate.of(2025, 3, 10))
                        .build(),

                Income.builder()
                        .amount(BigDecimal.valueOf(3100))
                        .dueDate(LocalDate.of(2025, 3, 10))
                        .build(),

                Income.builder()
                        .amount(BigDecimal.valueOf(3200))
                        .dueDate(LocalDate.of(2025, 3, 10))
                        .build()
        ));

    }
}
