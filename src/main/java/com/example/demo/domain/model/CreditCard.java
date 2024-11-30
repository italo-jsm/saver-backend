package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

public class CreditCard extends PaymentMethod{

    private Integer invoiceLastDay;
    private Integer invoiceFirstDay;
    private Integer invoiceDueDate;

    public BigDecimal calculateInvoiceSimpleExpenseTotalAmount(List<Expense> expenses, Month month, Year year){
        return expenses
                .stream()
                .filter(e -> e.paymentMethod().getName().equals(this.getName()))
                .filter(e -> e.installments() == 1)
                .filter(e -> e.expenseDate().isAfter(LocalDate.of(year.getValue(), month, invoiceFirstDay))
                        && e.expenseDate().isBefore(LocalDate.of(year.getValue(), month, invoiceLastDay)))
                .map(Expense::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal calculateInvoiceInInstallmentsExpenseTotalAmount(List<Expense> expenses, Month month, Year year){
        return expenses
                .stream()
                .filter(e -> e.paymentMethod().getName().equals(this.getName()))
                .filter(e -> e.installments() > 1)
                .map(Expense::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
