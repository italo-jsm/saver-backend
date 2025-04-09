package com.example.demo.domain.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BalanceTest {

    @Test
    public void lala(){
        Balance balance = Balance.builder().balanceDate(LocalDate.now()).balance(BigDecimal.valueOf(1000)).build();
        List<Income> incomes = List.of(
                Income.builder().build(),
                Income.builder().build(),
                Income.builder().build(),
                Income.builder().build(),
                Income.builder().build()
        );

        List<Bill> bills = List.of(
                Bill.builder().build(),
                Bill.builder().build(),
                Bill.builder().build(),
                Bill.builder().build(),
                Bill.builder().build()
        );
    }
}
