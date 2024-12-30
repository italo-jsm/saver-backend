package com.example.demo.infrastructure.persistence.jpa.entity;

import com.example.demo.enums.Fuel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "refuel")
public class RefuelEntity extends AbstractEntity{
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
