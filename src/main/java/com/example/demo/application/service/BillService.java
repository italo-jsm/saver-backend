package com.example.demo.application.service;

import com.example.demo.api.dto.BillDto;
import com.example.demo.application.mapper.BillMapper;
import com.example.demo.domain.model.Bill;
import com.example.demo.domain.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public String saveBill(BillDto bill){
        return billRepository.createBill(billMapper.toDomain(bill));
    }

    public List<BillDto> getAll(){
        return billRepository.getAll().stream().map(billMapper::toDto).toList();
    }
}
