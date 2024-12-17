package com.example.demo.domain.repository;

import com.example.demo.domain.model.Bill;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BillRepository {
    String createBill(Bill bill);
    List<Bill> getAll();
    List<Bill> findByPaymentMonthAndYear(int month, int year);
    List<Bill> findBillsByCreditCardId(String creditCardId);
    Optional<Bill> findBillByCreditCardIdAndDueDate(String creditCardId, LocalDate dueDate);
}
