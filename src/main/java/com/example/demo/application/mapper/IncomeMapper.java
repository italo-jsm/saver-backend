package com.example.demo.application.mapper;

import com.example.demo.api.dto.IncomeDto;
import com.example.demo.domain.model.Income;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncomeMapper {
    Income toDomain(IncomeDto incomeDto);
    IncomeDto toDto(Income income);
}
