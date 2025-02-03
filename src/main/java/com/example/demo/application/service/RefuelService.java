package com.example.demo.application.service;

import com.example.demo.domain.model.Refuel;
import com.example.demo.domain.repository.RefuelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RefuelService {

    private RefuelRepository refuelRepository;

    public String save(Refuel refuel){
        return refuelRepository.insert(refuel);
    }

    public Optional<Refuel> getRefuelById(String id){
        return refuelRepository.findRefuelById(id);
    }
}
