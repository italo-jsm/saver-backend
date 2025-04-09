package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "balance")
public class BalanceEntity extends AbstractEntity{
    private LocalDate balanceDate;
    private BigDecimal balance;
}
