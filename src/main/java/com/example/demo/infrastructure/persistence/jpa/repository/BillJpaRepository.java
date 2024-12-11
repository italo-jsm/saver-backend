package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Bill;
import com.example.demo.domain.repository.BillRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.BillDao;
import com.example.demo.infrastructure.persistence.jpa.mapper.BillEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BillJpaRepository implements BillRepository {

    private final BillDao billDao;
    private final BillEntityMapper billEntityMapper;

    @Override
    public String createBill(Bill bill) {
        return billDao.save(billEntityMapper.toEntity(bill)).getId();
    }

    @Override
    public List<Bill> getAll() {
        return billDao.findAll().stream().map(billEntityMapper::toDomain).toList();
    }

    @Override
    public List<Bill> findByPaymentMonthAndYear(int month, int year) {
        return billDao.findByBillMonthAndYear(month, year).stream().map(billEntityMapper::toDomain).toList();
    }
}
