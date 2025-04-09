package com.example.demo.api.dto;

import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Income;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDto {
    private LocalDate balanceDate;
    private BigDecimal balance;
    private List<Income> incomes;
    private List<Bill> bills;
}
