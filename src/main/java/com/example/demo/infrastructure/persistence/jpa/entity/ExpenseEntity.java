package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "expense")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    LocalDate expenseDate;
    BigDecimal amount;
    String description;
    String commercialEstablishmentName;
    Integer installments;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity categoryEntity;
    @ManyToOne
    @JoinColumn(name = "payment-method_id", nullable = false)
    PaymentMethodEntity paymentMethodEntity;
}
