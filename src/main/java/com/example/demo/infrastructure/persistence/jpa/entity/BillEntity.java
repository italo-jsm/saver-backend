package com.example.demo.infrastructure.persistence.jpa.entity;

import com.example.demo.enums.BillState;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bill")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    private String filePath;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String description;
    private BillState state;
    private String creditCardId;
}
