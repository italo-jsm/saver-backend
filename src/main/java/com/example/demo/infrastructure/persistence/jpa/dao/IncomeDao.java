package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.infrastructure.persistence.jpa.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IncomeDao extends JpaRepository<IncomeEntity, String> {

    @Query(value = "SELECT * FROM income WHERE (EXTRACT(MONTH FROM due_date) = :month AND EXTRACT(YEAR FROM due_date) = :year)",
            nativeQuery = true)
    List<IncomeEntity> findIncomeByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT i FROM IncomeEntity i WHERE DATE(i.dueDate) = :date")
    List<IncomeEntity> findIncomeByDate(@Param("date") LocalDate date);
}
