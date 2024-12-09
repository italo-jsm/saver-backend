package com.example.demo.api.dto;

import com.example.demo.enums.BillState;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillDto(
        String id,
        BigDecimal amount,
        LocalDate dueDate,
        String description,
        String filePath,
        BillState state
) {
}
