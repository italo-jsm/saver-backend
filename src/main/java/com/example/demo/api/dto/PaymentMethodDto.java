package com.example.demo.api.dto;

import lombok.Data;

@Data
public class PaymentMethodDto{
    private String id;
    private String name;
    private Integer invoiceClosingDay;
    private Integer invoiceDueDay;
}
