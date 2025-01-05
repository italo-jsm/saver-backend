package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Bill;
import com.example.demo.domain.repository.BillRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.BillDao;
import com.example.demo.infrastructure.persistence.jpa.entity.BillEntity;
import com.example.demo.infrastructure.persistence.jpa.mapper.BillEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BillJpaRepository implements BillRepository {

    private final BillDao billDao;
    private final BillEntityMapper billEntityMapper;

    @Override
    public String createBill(Bill bill) {
        BillEntity entity = billEntityMapper.toEntity(bill);
        return billDao.save(entity).getId();
    }

    @Override
    public List<Bill> getAll() {
        return billDao.findAll().stream().map(billEntityMapper::toDomain).toList();
    }

    @Override
    public List<Bill> findByPaymentMonthAndYear(int month, int year) {
        List<BillEntity> byBillMonthAndYear = billDao.findByBillMonthAndYear(month, year);
        return byBillMonthAndYear.stream().map(billEntityMapper::toDomain).toList();
    }

    @Override
    public List<Bill> findBillsByCreditCardId(String creditCardId) {
        return billDao.findByCreditCardId(creditCardId).stream().map(billEntityMapper::toDomain).toList();
    }

    @Override
    public Optional<Bill> findBillByCreditCardIdAndDueDate(String creditCardId, LocalDate dueDate) {
        return billDao.findByCreditCardIdAndDueDate(creditCardId, dueDate).map(billEntityMapper::toDomain);
    }
}
