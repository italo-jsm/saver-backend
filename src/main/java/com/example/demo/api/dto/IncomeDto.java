package com.example.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDto {
    private String description;
    private LocalDate dueDate;
    private Integer recurrence;
    private BigDecimal amount;
}
