package com.example.demo.domain.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreditCardInvoice {
    private PaymentMethod creditCard;
    private List<Expense> invoiceExpenses;
    private LocalDate dueDate;
}
