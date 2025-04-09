package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Balance {

    private LocalDate balanceDate;
    private BigDecimal balance;
    private List<Income> incomes;
    private List<Bill> bills;
}
