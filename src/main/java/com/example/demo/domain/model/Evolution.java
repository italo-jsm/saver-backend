package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Evolution {
    private BigDecimal amount;
    private int month;
    private int year;
}
