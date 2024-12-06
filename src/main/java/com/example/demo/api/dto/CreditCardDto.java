package com.example.demo.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreditCardDto extends PaymentMethodDto {
    private Integer invoiceClosingDay;
    private Integer invoiceDueDay;
    private boolean businessDayClosing;

    public boolean getBusinessDayClosing() {
        return this.businessDayClosing;
    }
}
