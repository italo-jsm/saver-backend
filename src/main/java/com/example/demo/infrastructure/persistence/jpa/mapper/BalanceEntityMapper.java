package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Balance;
import com.example.demo.infrastructure.persistence.jpa.entity.BalanceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceEntityMapper {
    BalanceEntity toEntity(Balance balance);
    Balance toDomain(BalanceEntity balance);
}
