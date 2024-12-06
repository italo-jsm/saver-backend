package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Bill;
import com.example.demo.infrastructure.persistence.jpa.entity.BillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillEntityMapper {
    BillEntity toEntity(Bill bill);
    Bill toDomain(BillEntity billEntity);
}
