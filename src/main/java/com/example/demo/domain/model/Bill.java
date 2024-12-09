package com.example.demo.domain.model;

import com.example.demo.enums.BillState;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Bill(
        String id,
        BigDecimal amount,
        LocalDate dueDate,
        String description,
        String filePath,
        BillState state
) {
}
