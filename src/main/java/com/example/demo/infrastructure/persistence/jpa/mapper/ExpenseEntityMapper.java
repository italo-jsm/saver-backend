package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Expense;
import com.example.demo.infrastructure.persistence.jpa.entity.ExpenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseEntityMapper {
    @Mapping(source = "category.id", target = "categoryEntity.id")
    @Mapping(source = "paymentMethod.id", target = "paymentMethodEntity.id")
    ExpenseEntity toEntity(Expense expense);
    @Mapping(source = "categoryEntity.name", target = "category.name")
    @Mapping(source = "paymentMethodEntity.name", target = "paymentMethod.name")
    Expense toDomain(ExpenseEntity expenseEntity);
}
