package com.example.demo.application.service;

import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.model.events.ExpenseCreatedEvent;
import com.example.demo.domain.repository.BillRepository;
import com.example.demo.enums.BillState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillService {
    private final BillRepository billRepository;
    private final PaymentMethodService paymentMethodService;
    private final InvoiceService invoiceService;

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

    public Bill setBillState(Bill bill){
        Bill toUpdate = billRepository.findById(bill.getId()).orElseThrow();
        toUpdate.setState(bill.getState());
        return billRepository.createBill(toUpdate);
    }

    @EventListener
    public void handleExpenseEvent(ExpenseCreatedEvent event){
        PaymentMethod paymentMethod = paymentMethodService.getById(event.getExpense().getPaymentMethod().getId());
        if (!paymentMethod.isCreditCard()) {
            saveBill(Bill.builder().state(BillState.PAYED).dueDate(LocalDate.now()).description(event.getExpense().getDescription()).amount(event.getExpense().getAmount()).build());
        }
        updateBills(event.getExpense(), event.getUpdateEvent());
    }

    public void updateBills(Expense expense, ExpenseCreatedEvent.UPDATE_EVENT event){
        PaymentMethod creditCard = paymentMethodService.getById(expense.getPaymentMethod().getId());
        if(creditCard.isCreditCard()){
            LocalDate firstPayment = expense.getFirstPayment();
            while(firstPayment.isBefore(expense.getLastPayment()) || firstPayment.isEqual(expense.getLastPayment())){
                Bill billToUpdate = billToUpdate(creditCard, firstPayment);
                billToUpdate.setAmount(invoiceService.createInvoice(expense.getPaymentMethod().getId(),
                        billToUpdate.getDueDate().getMonth().getValue(), billToUpdate.getDueDate().getYear()).getAmount());
                log.info("Updating bill {}", billToUpdate);
                this.saveBill(billToUpdate);
                firstPayment = firstPayment.plusMonths(1);
            }
        }
    }

    private Bill billToUpdate(PaymentMethod creditCard, LocalDate dueDate){
        Optional<Bill> billByCreditCardIdAndDueDate = this.findBillByCreditCardIdAndDueDate(creditCard.getId(), dueDate);
        return billByCreditCardIdAndDueDate.orElseGet(() -> {
            Bill bill = Bill.builder()
                    .state(BillState.TO_EXPIRE)
                    .amount(BigDecimal.ZERO)
                    .description("Fatura do " + creditCard.getName())
                    .creditCardId(creditCard.getId())
                    .dueDate(dueDate)
                    .creditCardId(creditCard.getId())
                    .build();
            return this.saveBill(bill);
        });
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

    public List<Bill> findBillsByDate(LocalDate date){
        return billRepository.findBillsByDate(date);
    }
}
