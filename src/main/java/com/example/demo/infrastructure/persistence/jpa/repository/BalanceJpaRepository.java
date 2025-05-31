package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Balance;
import com.example.demo.domain.repository.BalanceRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.BalanceDao;
import com.example.demo.infrastructure.persistence.jpa.entity.BalanceEntity;
import com.example.demo.infrastructure.persistence.jpa.mapper.BalanceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BalanceJpaRepository implements BalanceRepository {
    private final BalanceDao balanceDao;
    private final BalanceEntityMapper balanceEntityMapper;

    @Override
    public void createBalance(Balance balance) {
        balanceDao.save(balanceEntityMapper.toEntity(balance));
    }

    @Override
    public Balance findByBalanceDate(LocalDate date) {
        return balanceDao.findByBalanceDate(date).map(balanceEntityMapper::toDomain).orElse(null);
    }

    @Override
    public Balance findFirst() {
        return balanceEntityMapper.toDomain(balanceDao.findFirstByOrderByBalanceDateAsc());
    }

    @Override
    public Balance findLast() {
        return balanceEntityMapper.toDomain(balanceDao.findFirstByOrderByBalanceDateDesc());
    }
}
