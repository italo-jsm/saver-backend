package com.example.demo.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Batch {
    private Product product;
    private Double quantity;
    private Double amount;
    private LocalDate entranceDate;
}
