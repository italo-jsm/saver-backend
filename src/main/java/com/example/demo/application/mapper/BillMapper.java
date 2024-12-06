package com.example.demo.application.mapper;

import com.example.demo.api.dto.BillDto;
import com.example.demo.domain.model.Bill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill toDomain(BillDto billDto);
    BillDto toDto(Bill bill);
}
