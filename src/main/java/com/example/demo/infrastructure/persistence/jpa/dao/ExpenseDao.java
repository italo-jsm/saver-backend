package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.infrastructure.persistence.jpa.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

import java.util.List;

public interface ExpenseDao extends JpaRepository<ExpenseEntity, String> {

    @Query(value = "SELECT * FROM expense " +
            "WHERE EXTRACT(MONTH FROM expense_date) = :month " +
            "AND EXTRACT(YEAR FROM expense_date) = :year",
            nativeQuery = true)
    List<ExpenseEntity> findByExpenseMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query(value = """
                SELECT *
                FROM expense
                WHERE "paymentmethod_id" = :paymentMethodId
                  AND :reference BETWEEN DATE_TRUNC('month', first_payment)
                                     AND DATE_TRUNC('month', last_payment)
            """, nativeQuery = true)
    List<ExpenseEntity> findInvoiceExpenses(
            @Param("paymentMethodId") String paymentMethodId,
            @Param("reference") LocalDate reference
    );
}
