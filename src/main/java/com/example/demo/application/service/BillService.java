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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final BillMapper billMapper;

    public String saveBill(BillDto bill){
        if(bill.getState() != BillState.PAYED){
            if(bill.getDueDate().isEqual(LocalDate.now())){
                bill.setState(BillState.DUE_TODAY);
            }
            if(bill.getDueDate().isBefore(LocalDate.now())){
                bill.setState(BillState.LATE);
            }
        }
        return billRepository.createBill(billMapper.toDomain(bill));
    }


    public List<BillDto> getAll(){
        return billRepository.getAll().stream().map(billMapper::toDto).toList();
    }

    public List<BillDto> getBillsByMonthAndYear(int month, int year){
        List<Bill> byPaymentMonthAndYear = billRepository.findByPaymentMonthAndYear(month, year);
        return byPaymentMonthAndYear.stream().map(billMapper::toDto).toList();
    }

    public List<BillDto> getBillsByCreditCardId(String creditCardId){
        return billRepository.findBillsByCreditCardId(creditCardId).stream().map(billMapper::toDto).toList();
    }

    public Optional<BillDto> findBillByCreditCardIdAndDueDate(String creditCardId, LocalDate dueDate){
        Optional<BillDto> billDto = billRepository.findBillByCreditCardIdAndDueDate(creditCardId, dueDate).map(billMapper::toDto);
        return billDto;
    }

    public List<BillDto> findByDueDateBetween(LocalDate begin, LocalDate end) {
        return new ArrayList<>();
    }
}
