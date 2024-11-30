package com.example.demo.infrastructure.persistence.jpa.entity;

import com.example.demo.enums.Fuel;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "refuel")
public class RefuelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    private ExpenseEntity expense;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;
    private BigDecimal liters;
    private BigDecimal mileage;
    private Fuel fuel;
}
