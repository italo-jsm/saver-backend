package com.example.demo.api.dto;

import com.example.demo.enums.BillState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private String id;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String description;
    private String filePath;
    private BillState state;
    private String creditCardId;
    private String createdByUsername;
    private String updatedByUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}