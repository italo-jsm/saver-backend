package com.example.demo.infrastructure.persistence.jpa.entity;

import com.example.demo.enums.BillState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "bill")
public class BillEntity extends AbstractEntity{
    private String bankSlipFilePath;
    private String paymentReceiptFilePath;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String description;
    private BillState state;
    private String creditCardId;

}
