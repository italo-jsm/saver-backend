package com.example.demo.domain.repository;

import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Income;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BillRepository {
    Bill createBill(Bill bill);
    List<Bill> getAll();
    List<Bill> findByPaymentMonthAndYear(int month, int year);
    List<Bill> findBillsByCreditCardId(String creditCardId);
    Optional<Bill> findBillByCreditCardIdAndDueDate(String creditCardId, LocalDate dueDate);
    Optional<Bill> findById(String id);
    List<Bill> findBillsByDate(LocalDate date);
}
