package com.example.demo.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentMethodDto{
    private String id;
    private String name;
    private Integer invoiceClosingDay;
    private Integer invoiceDueDay;
    private BigDecimal balance;

    public boolean isCreditCard(){
        if(this.invoiceClosingDay == null){
            return false;
        }else return this.invoiceClosingDay != 0;
    }

    public boolean isAccount(){
        return balance != null;
    }
}
