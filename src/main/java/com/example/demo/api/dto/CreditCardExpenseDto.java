package com.example.demo.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Data
@Builder
public class CreditCardExpenseDto {
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;
}
