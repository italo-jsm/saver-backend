package com.example.demo.application.mapper;

import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.domain.model.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toDomain(ExpenseDto expenseDto);
    ExpenseDto toDto(Expense expense);
}
