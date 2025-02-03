package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CreditCardInvoice {
    private PaymentMethod creditCard;
    private List<CreditCardExpense> invoiceExpenses;
    private LocalDate dueDate;
    private BigDecimal amount;
}
