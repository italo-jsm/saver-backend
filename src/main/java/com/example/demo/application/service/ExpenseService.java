package com.example.demo.application.service;

import com.example.demo.domain.model.Bill;
import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.repository.ExpenseRepository;
import com.example.demo.domain.repository.PaymentMethodRepository;
import com.example.demo.enums.BillState;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodService paymentMethodService;
    private final BillService billService;

    public List<Expense> getAll(){
        return expenseRepository.findAll();
    }

    public String createExpense(Expense expense) {
        log.info("Creating expense {}", expense);
        expense.setLastPayment(expense.getExpenseDate());
        expense.setFirstPayment(expense.getExpenseDate());
        paymentMethodRepository.findById(expense.getPaymentMethod().getId())
                .ifPresent(paymentMethod -> {
                    expense.setFirstPayment(paymentMethod.firstPaymentDate(expense.getExpenseDate()));
                    expense.setLastPayment(paymentMethod.firstPaymentDate(expense.getExpenseDate()).plusMonths(expense.getInstallments() - 1));
                });
        updateBills(expense);
        return expenseRepository.insert(expense);
    }

    private void updateBills(Expense expense){
        PaymentMethod creditCard = paymentMethodService.getById(expense.getPaymentMethod().getId());
        if(creditCard.isCreditCard()){
            LocalDate firstPayment = expense.getFirstPayment();
            while(firstPayment.isBefore(expense.getLastPayment()) || firstPayment.isEqual(expense.getLastPayment())){
                Bill billToUpdate = billToUpdate(creditCard, firstPayment);
                billToUpdate.setAmount(billToUpdate.getAmount().add(expense.getAmount().divide(BigDecimal.valueOf(expense.getInstallments()), RoundingMode.CEILING)));
                log.info("Updating bill {}", billToUpdate);
                billService.saveBill(billToUpdate);
                firstPayment = firstPayment.plusMonths(1);
            }
        }
    }

    private Bill billToUpdate(PaymentMethod creditCard, LocalDate dueDate){
        Optional<Bill> billByCreditCardIdAndDueDate = billService.findBillByCreditCardIdAndDueDate(creditCard.getId(), dueDate);
        return billByCreditCardIdAndDueDate.orElseGet(() -> {
            Bill bill = Bill.builder()
                    .state(BillState.TO_EXPIRE)
                    .amount(BigDecimal.ZERO)
                    .description("Fatura do " + creditCard.getName())
                    .creditCardId(creditCard.getId())
                    .dueDate(dueDate)
                    .creditCardId(creditCard.getId())
                    .build();
            return billService.saveBill(bill);
        });
    }

    public Optional<Expense> getExpenseById(String id) {
        return expenseRepository.findById(id);
    }

    public List<Expense> getExpensesByPaymentMethod(PaymentMethod paymentMethod){
        return expenseRepository.findByPaymentMethodId(paymentMethod.getId());
    }

    public List<Expense> getExpensesByMonthAndYear(int month, int year){
        return expenseRepository.findByPaymentMonthAndYear(month, year);
    }


    public List<Expense> getInvoiceSummary(String creditCardId, YearMonth dueMonth){
        return expenseRepository.findInvoiceExpenses(creditCardId, dueMonth);
    }
}
