package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Income;
import com.example.demo.infrastructure.persistence.jpa.entity.IncomeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncomeEntityMapper {
    Income toDomain(IncomeEntity refuelEntity);
    IncomeEntity toEntity(Income income);
}
