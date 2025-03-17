package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Summary {
    BigDecimal totalIncomes;
    BigDecimal totalOutcomes;
    int month;
    int year;
}
