package com.example.demo.domain.model;

import com.example.demo.enums.BillState;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Bill {
    private String id;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String description;
    private String filePath;
    private BillState state;
    private String creditCardId;
    private String bankSlipFilePath;
    private String paymentReceiptFilePath;
    private String createdByUsername;
    private String updatedByUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}