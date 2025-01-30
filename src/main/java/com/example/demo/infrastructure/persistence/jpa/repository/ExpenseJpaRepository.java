package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Expense;
import com.example.demo.domain.repository.ExpenseRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.ExpenseDao;
import com.example.demo.infrastructure.persistence.jpa.mapper.ExpenseEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ExpenseJpaRepository implements ExpenseRepository {

    private final ExpenseDao expenseDao;
    private final ExpenseEntityMapper expenseEntityMapper;

    @Override
    public String insert(Expense expense) {
        return expenseDao.save(expenseEntityMapper.toEntity(expense)).getId();
    }

    @Override
    public void delete(Expense expense) {
        expenseDao.delete(expenseEntityMapper.toEntity(expense));
    }

    @Override
    public Optional<Expense> findById(String id) {
        return expenseDao.findById(id).map(expenseEntityMapper::toDomain);
    }

    @Override
    public List<Expense> findByPaymentMethodId(String paymentMethodId) {
        return List.of();
    }

    @Override
    public List<Expense> findByPaymentMonthAndYear(int month, int year) {
        return expenseDao.findByExpenseMonthAndYear(month, year).stream().map(expenseEntityMapper::toDomain).toList();
    }


    @Override
    public List<Expense> findAll() {
        return expenseDao.findAll().stream().map(expenseEntityMapper::toDomain).toList();
    }

    @Override
    public List<Expense> findInvoiceExpenses(String paymentMethodId, YearMonth reference) {
        return expenseDao.findInvoiceExpenses(paymentMethodId, reference.atDay(1)).stream().map(expenseEntityMapper::toDomain).toList();
    }

}
