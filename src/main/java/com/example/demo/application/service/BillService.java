package com.example.demo.application.service;

import com.example.demo.api.dto.BillDto;
import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.application.mapper.BillMapper;
import com.example.demo.domain.model.Bill;
import com.example.demo.domain.repository.BillRepository;
import com.example.demo.enums.BillState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public String saveBill(BillDto bill){
        BillState billState = bill.getState();
        if(bill.getState() != BillState.PAYED){
            if(bill.getDueDate().isEqual(LocalDate.now())){
                billState = BillState.DUE_TODAY;
            }
            if(bill.getDueDate().isBefore(LocalDate.now())){
                billState = BillState.LATE;
            }
        }
        return billRepository.createBill(billMapper.toDomain(new BillDto(bill.getId(), bill.getAmount(), bill.getDueDate(), bill.getDescription(), bill.getFilePath(), billState)));
    }

    public String updateBill(BillDto billDto){
        return "";
    }

    public List<BillDto> getAll(){
        return billRepository.getAll().stream().map(billMapper::toDto).toList();
    }

    public List<BillDto> getBillsByMonthAndYear(int month, int year){
        List<Bill> byPaymentMonthAndYear = billRepository.findByPaymentMonthAndYear(month, year);
        return byPaymentMonthAndYear.stream().map(billMapper::toDto).toList();
    }
}
