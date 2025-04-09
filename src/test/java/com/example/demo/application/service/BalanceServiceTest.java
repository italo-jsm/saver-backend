package com.example.demo.application.service;

import com.example.demo.domain.repository.BalanceRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {

    @Mock
    private IncomeService incomeService;

    @Mock
    private BillService billService;

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    @Test
    public void lala(){
        balanceService.createBalanceProjection(10);
    }
}
