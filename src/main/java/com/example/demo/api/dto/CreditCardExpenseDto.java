package com.example.demo.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Data
@Builder
public class CreditCardExpenseDto {
    private String description;
    private BigDecimal amount;
    private LocalDate expenseDate;

    public static CreditCardExpenseDto fromExpense(ExpenseDto expenseDto, Integer month, Integer year){
        String describedDescription = "";
        if(expenseDto.installments() == 1){
            describedDescription = expenseDto.description();
        }else{
            LocalDate current = LocalDate.of(year, month, expenseDto.lastPayment().getDayOfMonth());
            LocalDate last = expenseDto.lastPayment();
            int cont = 0;
            while(!current.isEqual(last)){
                cont ++;
                current = current.plusMonths(1);
            }
            describedDescription = expenseDto.description() + " Parcela " + (expenseDto.installments() - cont) + " de " + expenseDto.installments();
        }
        return CreditCardExpenseDto.builder()
                .expenseDate(expenseDto.expenseDate())
                .amount(expenseDto.amount().divide(BigDecimal.valueOf(expenseDto.installments()), RoundingMode.CEILING))
                .description(describedDescription)
                .build();
    }
}
