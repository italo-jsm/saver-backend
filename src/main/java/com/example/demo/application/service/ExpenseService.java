package com.example.demo.application.service;

import com.example.demo.api.dto.BillDto;
import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.api.dto.PaymentMethodDto;
import com.example.demo.application.mapper.BillMapper;
import com.example.demo.application.mapper.ExpenseMapper;
import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.repository.ExpenseRepository;
import com.example.demo.domain.repository.PaymentMethodRepository;
import com.example.demo.enums.BillState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final BillMapper billMapper;
    private final ExpenseRepository expenseRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodService paymentMethodService;
    private final BillService billService;

    public List<ExpenseDto> getAll(){
        return expenseRepository.findAll().stream().map(expenseMapper::toDto).toList();
    }

    public String createExpense(ExpenseDto expenseDto) {
        Expense expense = expenseMapper.toDomain(expenseDto);
        expense.setLastPayment(expenseDto.expenseDate());
        expense.setFirstPayment(expenseDto.expenseDate());
        paymentMethodRepository.findById(expenseDto.paymentMethod().getId())
                .ifPresent(paymentMethod -> {
                    expense.setFirstPayment(paymentMethod.firstPaymentDate(expenseDto.expenseDate()));
                    expense.setLastPayment(paymentMethod.firstPaymentDate(expenseDto.expenseDate()).plusMonths(expenseDto.installments() - 1));
                });
        updateBills(expense);
        return expenseRepository.insert(expense);
    }

    private void updateBills(Expense expense){
        List<BillDto> bills = billService.getBillsByCreditCardId(expense.getPaymentMethod().getId());
        PaymentMethodDto creditCard = paymentMethodService.getById(expense.getId());
        if(bills.isEmpty()){
            LocalDate firstPayment = expense.getFirstPayment();
            while(firstPayment.isBefore(expense.getLastPayment()) || firstPayment.isEqual(expense.getLastPayment())){
                Bill bill = Bill.builder()
                        .state(BillState.TO_EXPIRE)
                        .amount(BigDecimal.ZERO)
                        .description("Fatura do " + creditCard.getName())
                        .creditCardId(creditCard.getId())
                        .dueDate(firstPayment)
                        .build();
                billService.saveBill(billMapper.toDto(bill));
                firstPayment = firstPayment.plusMonths(1);
            }
        }
    }

    public Optional<ExpenseDto> getExpenseById(String id) {
        return expenseRepository.findById(id).map(expenseMapper::toDto);
    }

    public List<ExpenseDto> getExpensesByPaymentMethod(PaymentMethod paymentMethod){
        return expenseRepository.findByPaymentMethodId(paymentMethod.getId()).stream().map(expenseMapper::toDto).toList();
    }

    public List<ExpenseDto> getExpensesByMonthAndYear(int month, int year){
        return expenseRepository.findByPaymentMonthAndYear(month, year).stream().map(expenseMapper::toDto).toList();
    }


    public List<ExpenseDto> getInvoiceSummary(String creditCardId, YearMonth dueMonth){
        return expenseRepository.findInvoiceExpenses(creditCardId, dueMonth).stream().map(expenseMapper::toDto).toList();
    }
}
