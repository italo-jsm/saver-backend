package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.infrastructure.persistence.jpa.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillDao extends JpaRepository<BillEntity, String> {
    @Query(value = "SELECT * FROM bill " +
            "WHERE EXTRACT(MONTH FROM due_date) = :month " +
            "AND EXTRACT(YEAR FROM due_date) = :year",
            nativeQuery = true)
    List<BillEntity> findByBillMonthAndYear(@Param("month") int month, @Param("year") int year);

    List<BillEntity> findByCreditCardId(String creditCardId);
}
