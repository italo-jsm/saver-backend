package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "paymentmethod")
public class PaymentMethodEntity extends AbstractEntity{
    private String name;
    private Integer invoiceClosingDay;
    private Integer invoiceDueDay;
    private boolean businessDayClosing;

    public boolean getBusinessDayClosing() {
        return this.businessDayClosing;
    }
}
