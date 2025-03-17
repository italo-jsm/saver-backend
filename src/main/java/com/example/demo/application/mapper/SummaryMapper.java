package com.example.demo.application.mapper;

import com.example.demo.api.dto.SummaryDto;
import com.example.demo.domain.model.Summary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SummaryMapper {
    SummaryDto toDto(Summary summary);
    Summary toDomain(SummaryDto summaryDto);
}
