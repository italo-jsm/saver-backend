package com.example.demo.application.service;

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

    public Bill saveBill(Bill bill){
        if(bill.getState() != BillState.PAYED){
            if(bill.getDueDate().isEqual(LocalDate.now())){
                bill.setState(BillState.DUE_TODAY);
            }
            if(bill.getDueDate().isBefore(LocalDate.now())){
                bill.setState(BillState.LATE);
            }
        }
        return billRepository.createBill(bill);
    }


    public List<Bill> getAll(){
        return billRepository.getAll();
    }

    public List<Bill> getBillsByMonthAndYear(int month, int year){
        return billRepository.findByPaymentMonthAndYear(month, year);
    }

    public List<Bill> getBillsByCreditCardId(String creditCardId){
        return billRepository.findBillsByCreditCardId(creditCardId);
    }

    public Optional<Bill> findBillByCreditCardIdAndDueDate(String creditCardId, LocalDate dueDate){
        return  billRepository.findBillByCreditCardIdAndDueDate(creditCardId, dueDate);
    }

    public List<Bill> findByDueDateBetween(LocalDate begin, LocalDate end) {
        //TODO
        return new ArrayList<>();
    }
}
