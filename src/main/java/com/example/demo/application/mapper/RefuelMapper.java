package com.example.demo.application.mapper;

import com.example.demo.api.dto.RefuelDto;
import com.example.demo.domain.model.Refuel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefuelMapper {
    RefuelDto toDto(Refuel refuel);
    Refuel toDomain(RefuelDto refuelDto);
}
