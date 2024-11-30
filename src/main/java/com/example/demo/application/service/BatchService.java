package com.example.demo.application.service;

import com.example.demo.api.dto.BatchDto;
import com.example.demo.application.mapper.BatchMapper;
import com.example.demo.domain.repository.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;
    private final BatchMapper batchMapper;

    public String createBatch(BatchDto batchDto){
        return batchRepository.insert(batchMapper.toDomain(batchDto));
    }

    public Optional<BatchDto> getBatchById(String id) {
        return batchRepository.findById(id).map(batchMapper::toDto);
    }
}
