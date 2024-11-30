package com.example.demo.api.dto;

import java.time.LocalDate;

public record BatchDto (
    String productSku,
    Double quantity,
    Double amount,
    LocalDate entranceDate
){}

