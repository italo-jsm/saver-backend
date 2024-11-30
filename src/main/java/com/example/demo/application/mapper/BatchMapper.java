package com.example.demo.application.mapper;

import com.example.demo.api.dto.BatchDto;
import com.example.demo.domain.model.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BatchMapper {
    @Mapping(source = "productSku", target = "product.sku")
    Batch toDomain(BatchDto batchDto);
    @Mapping(source = "product.sku", target = "productSku")
    BatchDto toDto(Batch batch);
}
