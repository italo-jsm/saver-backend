package com.example.demo.application.service;

import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.application.mapper.ExpenseMapper;
import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.repository.CategoryRepository;
import com.example.demo.domain.repository.ExpenseRepository;
import com.example.demo.domain.repository.PaymentMethodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;

    public String createExpense(ExpenseDto expenseDto){
        return expenseRepository.insert(expenseMapper.toDomain(expenseDto));
    }

    public Optional<ExpenseDto> getExpenseById(String id) {
        return expenseRepository.findById(id).map(expenseMapper::toDto);
    }

    public List<ExpenseDto> getExpensesByPaymentMethod(PaymentMethod paymentMethod){
        return expenseRepository.findByPaymentMethodId(paymentMethod.getId()).stream().map(expenseMapper::toDto).toList();
    }
}
