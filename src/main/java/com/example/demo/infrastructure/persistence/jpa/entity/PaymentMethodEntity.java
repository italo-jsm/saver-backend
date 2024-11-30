package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "payment-method")
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    private String name;
    @OneToMany(mappedBy = "paymentMethodEntity")
    private List<ExpenseEntity> expenses;
}
