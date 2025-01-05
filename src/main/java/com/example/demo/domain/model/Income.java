package com.example.demo.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Income {
    private String description;
    private LocalDate endDate;
    private String dueData;
    private BigDecimal amount;

    public boolean isRecurring(){
        return this.endDate == null;
    }
}
