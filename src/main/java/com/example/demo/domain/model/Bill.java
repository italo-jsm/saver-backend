package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Bill(
        BigDecimal amount,
        LocalDate dueDate,
        String description
) {
}
