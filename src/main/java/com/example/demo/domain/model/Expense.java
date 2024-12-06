package com.example.demo.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Expense {
    private String id;
    private LocalDate expenseDate;
    private LocalDate firstPayment;
    private LocalDate lastPayment;
    private BigDecimal amount;
    private String description;
    private String commercialEstablishmentName;
    private Integer installments;
    private PaymentMethod paymentMethod;
    private Category category;
}
