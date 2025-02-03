package com.example.demo.application.mapper;

import com.example.demo.api.dto.CreditCardInvoiceDto;
import com.example.demo.domain.model.CreditCardInvoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    CreditCardInvoice toDomain(CreditCardInvoiceDto invoiceDto);
    CreditCardInvoiceDto toDto(CreditCardInvoice invoice);
}
