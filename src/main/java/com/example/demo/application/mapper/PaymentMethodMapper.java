package com.example.demo.application.mapper;

import com.example.demo.api.dto.PaymentMethodDto;
import com.example.demo.domain.model.PaymentMethod;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethod toDomain(PaymentMethodDto paymentMethodDto);
    PaymentMethodDto toDto(PaymentMethod paymentMethod);
}
