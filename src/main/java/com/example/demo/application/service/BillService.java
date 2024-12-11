package com.example.demo.application.service;

import com.example.demo.api.dto.BillDto;
import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.application.mapper.BillMapper;
import com.example.demo.domain.repository.BillRepository;
import com.example.demo.enums.BillState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public String saveBill(BillDto bill){

        return billRepository.createBill(billMapper.toDomain(new BillDto(bill.id(), bill.amount(), bill.dueDate(), bill.description(), bill.filePath(), BillState.TO_EXPIRE)));
    }

    public List<BillDto> getAll(){
        return billRepository.getAll().stream().map(billMapper::toDto).toList();
    }

    public List<BillDto> getBillsByMonthAndYear(int month, int year){
        return billRepository.findByPaymentMonthAndYear(month, year).stream().map(billMapper::toDto).toList();
    }
}
