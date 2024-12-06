package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

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
    LocalDate firstPayment;
    LocalDate lastPayment;
    BigDecimal amount;
    String description;
    String commercialEstablishmentName;
    Integer installments;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    CategoryEntity categoryEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment-method_id", nullable = false)
    PaymentMethodEntity paymentMethodEntity;
}
