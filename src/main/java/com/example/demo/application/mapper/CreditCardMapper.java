package com.example.demo.application.mapper;

import com.example.demo.api.dto.CreditCardDto;
import com.example.demo.domain.model.CreditCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    default CreditCard toDomain(CreditCardDto creditCardDto){
        CreditCard creditCard = new CreditCard();
        creditCard.setId(creditCardDto.getId());
        creditCard.setName(creditCardDto.getName());
        creditCard.setInvoiceClosingDay(creditCardDto.getInvoiceClosingDay());
        creditCard.setBusinessDayClosing(creditCardDto.getBusinessDayClosing());
        creditCard.setInvoiceDueDay(creditCardDto.getInvoiceDueDay());
        return creditCard;
    }
    default CreditCardDto toDto(CreditCard creditCard){
        CreditCardDto creditCardDto = new CreditCardDto();
        creditCardDto.setId(creditCard.getId());
        creditCardDto.setName(creditCard.getName());
        creditCardDto.setInvoiceClosingDay(creditCard.getInvoiceClosingDay());
        creditCardDto.setBusinessDayClosing(creditCard.getBusinessDayClosing());
        creditCardDto.setInvoiceDueDay(creditCard.getInvoiceDueDay());
        return creditCardDto;
    }

}
