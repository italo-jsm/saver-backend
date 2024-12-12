package com.example.demo.application.service;

import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.application.mapper.ExpenseMapper;
import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.repository.ExpenseRepository;
import com.example.demo.domain.repository.PaymentMethodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public List<ExpenseDto> getAll(){
        return expenseRepository.findAll().stream().map(expenseMapper::toDto).toList();
    }

    public String createExpense(ExpenseDto expenseDto) {
        Expense expense = expenseMapper.toDomain(expenseDto);
        expense.setLastPayment(expenseDto.expenseDate());
        expense.setFirstPayment(expenseDto.expenseDate());
        paymentMethodRepository.findById(expenseDto.paymentMethod().getId())
                .ifPresent(paymentMethod -> {
                    expense.setFirstPayment(paymentMethod.firstPaymentDate(expenseDto.expenseDate()));
                    expense.setLastPayment(paymentMethod.firstPaymentDate(expenseDto.expenseDate()).plusMonths(expenseDto.installments() - 1));
                });
        return expenseRepository.insert(expense);
    }

    public Optional<ExpenseDto> getExpenseById(String id) {
        return expenseRepository.findById(id).map(expenseMapper::toDto);
    }

    public List<ExpenseDto> getExpensesByPaymentMethod(PaymentMethod paymentMethod){
        return expenseRepository.findByPaymentMethodId(paymentMethod.getId()).stream().map(expenseMapper::toDto).toList();
    }

    public List<ExpenseDto> getExpensesByMonthAndYear(int month, int year){
        return expenseRepository.findByPaymentMonthAndYear(month, year).stream().map(expenseMapper::toDto).toList();
    }


    public List<ExpenseDto> getInvoiceSummary(String creditCardId, YearMonth dueMonth){
        return expenseRepository.findInvoiceExpenses(creditCardId, dueMonth).stream().map(expenseMapper::toDto).toList();
    }
}
