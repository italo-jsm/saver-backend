package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Income {
    private String description;
    private LocalDate endDate;
    private LocalDate dueDate;
    private BigDecimal amount;

    public boolean isRecurring(){
        return this.endDate == null;
    }
}
