package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.infrastructure.persistence.jpa.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BalanceDao extends JpaRepository<BalanceEntity, String> {

    BalanceEntity findFirstByOrderByBalanceDateAsc();

    BalanceEntity findFirstByOrderByBalanceDateDesc();

    Optional<BalanceEntity> findByBalanceDate(LocalDate balanceDate);
}
