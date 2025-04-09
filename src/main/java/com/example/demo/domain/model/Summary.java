package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Summary {
    private BigDecimal totalIncomes;
    private BigDecimal totalOutcomes;
    private int month;
    private int year;
}
