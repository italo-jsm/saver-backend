package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Expense(
        String id,
        LocalDate expenseDate,
        BigDecimal amount,
        String description,
        String commercialEstablishmentName,
        Integer installments,
        PaymentMethod paymentMethod,
        Category category
) {
}
