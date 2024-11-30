package com.example.demo.application.service;

import com.example.demo.api.dto.RefuelDto;
import com.example.demo.application.mapper.RefuelMapper;
import com.example.demo.domain.repository.RefuelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RefuelService {

    private RefuelRepository refuelRepository;
    private RefuelMapper refuelMapper;

    public String save(RefuelDto refuelDto){
        return refuelRepository.insert(refuelMapper.toDomain(refuelDto));
    }

    public Optional<RefuelDto> getRefuelById(String id){
        return refuelRepository.findRefuelById(id).map(refuelMapper::toDto);
    }
}
