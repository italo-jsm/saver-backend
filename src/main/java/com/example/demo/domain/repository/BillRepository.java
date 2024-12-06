package com.example.demo.domain.repository;

import com.example.demo.domain.model.Bill;

import java.util.List;

public interface BillRepository {
    String createBill(Bill bill);
    List<Bill> getAll();
}
