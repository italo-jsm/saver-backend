package com.example.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDto {
    BigDecimal totalIncomes;
    BigDecimal totalOutcomes;
    int month;
    int year;
}
