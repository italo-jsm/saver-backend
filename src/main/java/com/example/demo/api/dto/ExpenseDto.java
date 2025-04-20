package com.example.demo.api.dto;


import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ExpenseDto(
        String id,
        LocalDate expenseDate,
        LocalDate firstPayment,
        LocalDate lastPayment,
        BigDecimal amount,
        String description,
        String commercialEstablishmentName,
        Integer installments,
        CategoryDto category,
        PaymentMethodDto paymentMethod
) {}
