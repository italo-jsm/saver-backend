package com.example.demo.domain.model;

import com.example.demo.enums.BillState;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Bill {
    private String id;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String description;
    private String filePath;
    private BillState state;
    private boolean creditCardInvoice;
}