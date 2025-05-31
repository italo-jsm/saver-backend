package com.example.demo.application.service;

import com.example.demo.domain.model.Balance;
import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Income;
import com.example.demo.domain.model.Summary;
import com.example.demo.domain.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final IncomeService incomeService;
    private final BillService billService;
    private final BalanceRepository balanceRepository;

    public List<Balance> createProjection(int days){
        List<Balance> projection = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Balance lastBalance = balanceRepository.findLast();
        if(lastBalance == null){
            Balance balance = Balance.builder().balance(BigDecimal.ZERO).balanceDate(LocalDate.now()).bills(Collections.emptyList()).incomes(Collections.emptyList()).build();
            balanceRepository.createBalance(balance);
            lastBalance = balanceRepository.findLast();
        }

        for(int i = 0 ; i < days ; i++){
            List<Income> incomesByDate = incomeService.findIncomesByDate(date);
            BigDecimal totalIncomes = incomesByDate.stream().map(Income::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            List<Bill> billsByDate = billService.findBillsByDate(date);
            BigDecimal totalBills = billsByDate.stream().map(Bill::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            Balance currentBalance = Balance.builder()
                    .balanceDate(date)
                    .balance(lastBalance.getBalance().add(totalIncomes).subtract(totalBills))
                    .bills(billsByDate)
                    .incomes(incomesByDate)
                    .build();
            projection.add(currentBalance);
            lastBalance = currentBalance;
            date = date.plusDays(1);
        }

        return projection;
    }

    public List<Summary> getSummary(LocalDate begin, LocalDate end){
        List<Summary> summaryList = new ArrayList<>();
        while(begin.isBefore(end)){
            List<Income> incomes = incomeService.getIncomesByMonthAndYear(begin.getMonth().getValue(), begin.getYear());
            List<Bill> outcomes = billService.getBillsByMonthAndYear(begin.getMonth().getValue(), begin.getYear());
            summaryList.add(
                    Summary.builder()
                            .totalOutcomes(outcomes.stream().map(Bill::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                            .totalIncomes(incomes.stream().map(Income::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                            .month(begin.getMonth().getValue())
                            .year(begin.getYear())
                            .build()
            );
            begin = begin.plusMonths(1);
        }
        return summaryList;
    }

    @Scheduled(cron = "0 15 0 * * *")
    public void createDailyBalance(){
        log.info("Updating balances");
        LocalDate today = LocalDate.now();
        Balance lastBalance = balanceRepository.findLast();
        List<Income> incomesByDate = incomeService.findIncomesByDate(today);
        BigDecimal totalIncomes = incomesByDate.stream().map(Income::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        List<Bill> billsByDate = billService.findBillsByDate(today);
        BigDecimal totalBills = billsByDate.stream().map(Bill::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        Balance currentBalance = Balance.builder()
                .balanceDate(today)
                .balance(lastBalance.getBalance().add(totalIncomes).subtract(totalBills))
                .bills(billsByDate)
                .incomes(incomesByDate)
                .build();
        balanceRepository.createBalance(currentBalance);
    }
}
