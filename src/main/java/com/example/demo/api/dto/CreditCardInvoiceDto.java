package com.example.demo.api.dto;

import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CreditCardInvoiceDto {
    private PaymentMethodDto creditCard;
    private List<CreditCardExpenseDto> invoiceExpenses;
    private LocalDate dueDate;
    private BigDecimal amount;
}
