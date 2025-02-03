package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "income")
public class IncomeEntity extends AbstractEntity{
    private String description;
    private LocalDate endDate;
    private BigDecimal amount;
    private LocalDate dueDate;
}
