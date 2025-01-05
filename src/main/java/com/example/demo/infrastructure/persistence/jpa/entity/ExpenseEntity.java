package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "expense")
public class ExpenseEntity extends AbstractEntity{
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
    @JoinColumn(name = "paymentmethod_id", nullable = false)
    PaymentMethodEntity paymentMethodEntity;
}
