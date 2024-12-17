package com.example.demo.application.service;

import com.example.demo.api.dto.CreditCardInvoiceDto;
import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.api.dto.PaymentMethodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final ExpenseService expenseService;
    private final PaymentMethodService paymentMethodService;

    public CreditCardInvoiceDto createInvoice(String creditCardId, Integer month, Integer year){
        PaymentMethodDto paymentMethodDto = paymentMethodService.getById(creditCardId);
        if(paymentMethodDto.getInvoiceClosingDay() == null){
            throw new RuntimeException("Payment Method Must Be a Credit Card!");
        }else{
            List<ExpenseDto> invoiceSummary = expenseService.getInvoiceSummary(creditCardId, YearMonth.of(year, month));
            return CreditCardInvoiceDto
                    .builder()
                    .invoiceExpenses(invoiceSummary)
                    .creditCard(paymentMethodDto)
                    .dueDate(LocalDate.of(year, month, paymentMethodDto.getInvoiceDueDay()))
                    .amount(invoiceSummary.stream().map(it -> it.amount().divide(BigDecimal.valueOf(it.installments()))).reduce(BigDecimal::add).orElseThrow())
                    .build();
        }
    }
}
