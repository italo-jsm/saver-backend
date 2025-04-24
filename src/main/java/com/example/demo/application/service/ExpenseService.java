package com.example.demo.application.service;

import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.model.events.ExpenseCreatedEvent;
import com.example.demo.domain.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final PaymentMethodService paymentMethodService;
    private final ApplicationEventPublisher publisher;

    public List<Expense> getAll(){
        return expenseRepository.findAll();
    }

    public String createExpense(Expense expense) {
        log.info("Creating expense {}", expense);
        expense.setLastPayment(expense.getExpenseDate());
        expense.setFirstPayment(expense.getExpenseDate());
        PaymentMethod paymentMethod = paymentMethodService.getById(expense.getPaymentMethod().getId());
        expense.setFirstPayment(paymentMethod.firstPaymentDate(expense.getExpenseDate()));
        expense.setLastPayment(paymentMethod.firstPaymentDate(expense.getExpenseDate()).plusMonths(expense.getInstallments() - 1));
        String createdExpense = expenseRepository.insert(expense);
        publisher.publishEvent(new ExpenseCreatedEvent(expense));
        return createdExpense;
    }

    public void  deleteExpense(String expenseId){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow();
        expenseRepository.delete(expense);
        publisher.publishEvent(new ExpenseCreatedEvent(expense));
    }

    public Optional<Expense> getExpenseById(String id) {
        return expenseRepository.findById(id);
    }

    public List<Expense> getExpensesByMonthAndYear(int month, int year){
        return expenseRepository.findByPaymentMonthAndYear(month, year);
    }


    public List<Expense> getExpensesByPaymentMethodAndDueDate(String creditCardId, YearMonth dueMonth){
        return expenseRepository.findInvoiceExpenses(creditCardId, dueMonth);
    }

    public List<Expense> installmentsByMonthAndYear(Integer month, Integer year){
        return paymentMethodService.getAll().stream()
                .flatMap(it -> this.getExpensesByPaymentMethodAndDueDate(it.getId(), YearMonth.of(year, month)).stream())
                .filter(ex -> ex.getInstallments() > 1)
                .toList();
    }
}
