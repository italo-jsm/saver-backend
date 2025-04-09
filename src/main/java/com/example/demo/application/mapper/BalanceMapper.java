package com.example.demo.application.mapper;

import com.example.demo.api.dto.BalanceDto;
import com.example.demo.domain.model.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    Balance toDomain(BalanceDto balanceDto);
    BalanceDto toDto(Balance balance);
}
