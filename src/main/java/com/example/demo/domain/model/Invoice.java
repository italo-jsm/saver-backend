package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Invoice {

    private final List<Expense> expenses;

    public Invoice(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public BigDecimal getInvoiceProjectionByPaymentMethod(LocalDate date, PaymentMethod paymentMethod){
        return expenses
                .stream()
                .filter(e -> e.paymentMethod().name().equals(paymentMethod.name()))
                .filter(e -> e.expenseDate().plusMonths(e.installments()).isAfter(date) || e.expenseDate().plusMonths(e.installments()).isEqual(date))
                .map(Expense::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

