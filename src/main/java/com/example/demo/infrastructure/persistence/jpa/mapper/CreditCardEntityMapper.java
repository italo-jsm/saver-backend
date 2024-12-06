package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.api.dto.CreditCardDto;
import com.example.demo.domain.model.CreditCard;
import com.example.demo.infrastructure.persistence.jpa.entity.CreditCardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardEntityMapper {


    default CreditCard toDomain(CreditCardEntity creditCardEntity){
        CreditCard creditCard = new CreditCard();
        creditCard.setId(creditCardEntity.getId());
        creditCard.setName(creditCardEntity.getName());
        creditCard.setInvoiceClosingDay(creditCardEntity.getInvoiceClosingDay());
        creditCard.setBusinessDayClosing(creditCardEntity.getBusinessDayClosing());
        creditCard.setInvoiceDueDay(creditCardEntity.getInvoiceDueDay());
        return creditCard;
    }
    default CreditCardEntity toEntity(CreditCard creditCard){
        CreditCardEntity creditCardEntity = new CreditCardEntity();
        creditCardEntity.setId(creditCard.getId());
        creditCardEntity.setName(creditCard.getName());
        creditCardEntity.setInvoiceClosingDay(creditCard.getInvoiceClosingDay());
        creditCardEntity.setBusinessDayClosing(creditCard.getBusinessDayClosing());
        creditCardEntity.setInvoiceDueDay(creditCard.getInvoiceDueDay());
        return creditCardEntity;
    }

}
