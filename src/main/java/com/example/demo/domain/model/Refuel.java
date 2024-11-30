package com.example.demo.domain.model;

import com.example.demo.enums.Fuel;

import java.math.BigDecimal;

public record Refuel(
        String id,
        Expense expense,
        Vehicle vehicle,
        BigDecimal liters,
        Fuel fuel,
        Integer mileage
) {
}
