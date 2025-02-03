package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Data
@Builder
public class CreditCardExpense {
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private PaymentMethod paymentMethod;

    public static CreditCardExpense fromExpense(Expense expense, Integer month, Integer year){
        String describedDescription = "";
        if(expense.getInstallments() == 1){
            describedDescription = expense.getDescription();
        }else{
            LocalDate current = LocalDate.of(year, month, expense.getLastPayment().getDayOfMonth());
            LocalDate last = expense.getLastPayment();
            int cont = 0;
            while(!current.isEqual(last)){
                cont ++;
                current = current.plusMonths(1);
            }
            describedDescription = expense.getDescription() + " Parcela " + (expense.getInstallments() - cont) + " de " + expense.getInstallments();
        }
        return CreditCardExpense.builder()
                .expenseDate(expense.getExpenseDate())
                .amount(expense.getAmount().divide(BigDecimal.valueOf(expense.getInstallments()), RoundingMode.CEILING))
                .description(describedDescription)
                .paymentMethod(expense.getPaymentMethod())
                .build();
    }
}
