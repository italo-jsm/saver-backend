package com.example.demo.application.service;

import com.example.demo.domain.model.CreditCardExpense;
import com.example.demo.domain.model.CreditCardInvoice;
import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final ExpenseService expenseService;
    private final PaymentMethodService paymentMethodService;

    public CreditCardInvoice createInvoice(String creditCardId, Integer month, Integer year){
        PaymentMethod paymentMethod = paymentMethodService.getById(creditCardId);
        if(paymentMethod.getInvoiceClosingDay() == null){
            throw new RuntimeException("Payment Method Must Be a Credit Card!");
        }else{
            List<Expense> invoiceSummary = expenseService.getInvoiceSummary(creditCardId, YearMonth.of(year, month));

            return CreditCardInvoice
                    .builder()
                    .invoiceExpenses(invoiceSummary.stream().map(expense -> CreditCardExpense.fromExpense(expense, month, year)).toList())
                    .creditCard(paymentMethod)
                    .dueDate(LocalDate.of(year, month, paymentMethod.getInvoiceDueDay()))
                    .amount(invoiceSummary
                            .stream()
                            .map(it -> it.getAmount().divide(BigDecimal.valueOf(it.getInstallments()), RoundingMode.CEILING))
                            .reduce(BigDecimal::add)
                            .orElseGet(() -> BigDecimal.ZERO))
                    .build();
        }
    }
}
