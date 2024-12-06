package com.example.demo.infrastructure.persistence.jpa.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CreditCard")
@Data
public class CreditCardEntity extends PaymentMethodEntity{
    private Integer invoiceClosingDay;
    private Integer invoiceDueDay;
    private boolean businessDayClosing;

    public boolean getBusinessDayClosing() {
        return this.businessDayClosing;
    }
}
