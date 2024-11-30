package com.example.demo.api.dto;

import com.example.demo.enums.Fuel;

import java.math.BigDecimal;

public record RefuelDto(
        ExpenseDto expense,
        VehicleDto vehicle,
        BigDecimal liters,
        Fuel fuel,
        Integer mileage
) {
}
