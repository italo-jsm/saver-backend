package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.infrastructure.persistence.jpa.entity.PaymentMethodEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodEntityMapper {
    PaymentMethodEntity toEntity(PaymentMethod paymentMethod);
    PaymentMethod toDomain(PaymentMethodEntity paymentMethodEntity);
}
