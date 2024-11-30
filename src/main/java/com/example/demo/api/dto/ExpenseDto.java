package com.example.demo.api.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDto(
        String id,
        LocalDate expenseDate,
        BigDecimal amount,
        String description,
        String commercialEstablishmentName,
        Integer installments,
        CategoryDto category,
        PaymentMethodDto paymentMethod
) {}
