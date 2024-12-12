package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "payment-method")
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    private String name;
    private Integer invoiceClosingDay;
    private Integer invoiceDueDay;
    private boolean businessDayClosing;

    public boolean getBusinessDayClosing() {
        return this.businessDayClosing;
    }
}
