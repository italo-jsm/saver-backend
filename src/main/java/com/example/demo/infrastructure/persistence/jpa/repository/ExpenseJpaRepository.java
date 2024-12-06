package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.CreditCard;
import com.example.demo.domain.model.Expense;
import com.example.demo.domain.repository.ExpenseRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.ExpenseDao;
import com.example.demo.infrastructure.persistence.jpa.entity.CreditCardEntity;
import com.example.demo.infrastructure.persistence.jpa.mapper.CreditCardEntityMapper;
import com.example.demo.infrastructure.persistence.jpa.mapper.ExpenseEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ExpenseJpaRepository implements ExpenseRepository {

    private final ExpenseDao expenseDao;
    private final ExpenseEntityMapper expenseEntityMapper;
    private final CreditCardEntityMapper creditCardEntityMapper;

    @Override
    public String insert(Expense expense) {
        return expenseDao.save(expenseEntityMapper.toEntity(expense)).getId();
    }

    @Override
    public Optional<Expense> findById(String id) {
        return expenseDao.findById(id).map(expenseEntityMapper::toDomain);
    }

    @Override
    public List<Expense> findByPaymentMethodId(String paymentMethodId) {
        return List.of();
    }

    @Override
    public List<Expense> findByPaymentMonthAndYear(int month, int year) {
        return expenseDao.findByExpenseMonthAndYear(month, year).stream().map(expenseEntityMapper::toDomain).toList();
    }


    @Override
    public List<Expense> findAll() {
        return expenseDao.findAll().stream().map(expenseEntityMapper::toDomain).toList();
    }

    @Override
    public List<Expense> findInvoiceExpenses(String paymentMethodId, YearMonth reference) {
        return expenseDao.findInvoiceExpenses(paymentMethodId, reference.atDay(1)).stream().map(expenseEntityMapper::toDomain).toList();
    }

    private void insertFirstPaymentValue(){
        expenseDao.findAll().forEach(expenseEntity -> {
            if(Objects.equals(expenseEntity.getPaymentMethodEntity().getId(), "478e0b43-fa82-4633-bace-a06db4519469")){//PIX
                expenseEntity.setFirstPayment(expenseEntity.getExpenseDate());
                expenseEntity.setLastPayment(expenseEntity.getFirstPayment().plusMonths(expenseEntity.getInstallments() - 1));
                expenseDao.save(expenseEntity);
            }

            if(Objects.equals(expenseEntity.getPaymentMethodEntity().getId(), "e6a8aa5e-066b-4521-a5ce-cfdab497d846")){ //Caixa Credito
                CreditCardEntity creditCardEntity = (CreditCardEntity) expenseEntity.getPaymentMethodEntity();
                CreditCard creditCard = creditCardEntityMapper.toDomain(creditCardEntity);
                LocalDate invoiceFirstDay = creditCard.calculateInvoiceFirstDay(YearMonth.of(expenseEntity.getExpenseDate().getYear(), expenseEntity.getExpenseDate().getMonth()));
                if(expenseEntity.getExpenseDate().isAfter(invoiceFirstDay.minusDays(1))){
                    LocalDate actualDue = LocalDate.of(expenseEntity.getExpenseDate().getYear(), expenseEntity.getExpenseDate().getMonth(), creditCard.getInvoiceDueDay());
                    expenseEntity.setFirstPayment(actualDue.plusMonths(1));
                }else{
                    expenseEntity.setFirstPayment(expenseEntity.getExpenseDate());
                }
                expenseEntity.setLastPayment(expenseEntity.getFirstPayment().plusMonths(expenseEntity.getInstallments() - 1));
                expenseDao.save(expenseEntity);
            }

            if(Objects.equals(expenseEntity.getPaymentMethodEntity().getId(), "d6dba4d5-2d5f-44c4-b3c5-65f67c703901")){ //Nubank Credito
                CreditCardEntity creditCardEntity = (CreditCardEntity) expenseEntity.getPaymentMethodEntity();
                CreditCard creditCard = creditCardEntityMapper.toDomain(creditCardEntity);
                LocalDate invoiceFirstDay = creditCard.calculateInvoiceFirstDay(YearMonth.of(expenseEntity.getExpenseDate().getYear(), expenseEntity.getExpenseDate().getMonth()));
                if(expenseEntity.getExpenseDate().isAfter(invoiceFirstDay.minusDays(1))){
                    LocalDate actualDue = LocalDate.of(expenseEntity.getExpenseDate().getYear(), expenseEntity.getExpenseDate().getMonth(), creditCard.getInvoiceDueDay());
                    expenseEntity.setFirstPayment(actualDue.plusMonths(1));
                }else{
                    expenseEntity.setFirstPayment(expenseEntity.getExpenseDate());
                }
                expenseEntity.setLastPayment(expenseEntity.getFirstPayment().plusMonths(expenseEntity.getInstallments() - 1));
                expenseDao.save(expenseEntity);
            }

            if(Objects.equals(expenseEntity.getPaymentMethodEntity().getId(), "17cff301-3b36-4a1e-a618-cf8c7754950a")){ //WIll Credito
                CreditCardEntity creditCardEntity = (CreditCardEntity) expenseEntity.getPaymentMethodEntity();
                CreditCard creditCard = creditCardEntityMapper.toDomain(creditCardEntity);
                LocalDate invoiceFirstDay = creditCard.calculateInvoiceFirstDay(YearMonth.of(expenseEntity.getExpenseDate().getYear(), expenseEntity.getExpenseDate().getMonth()));
                if(expenseEntity.getExpenseDate().isAfter(invoiceFirstDay.minusDays(1))){
                    LocalDate actualDue = LocalDate.of(expenseEntity.getExpenseDate().getYear(), expenseEntity.getExpenseDate().getMonth(), creditCard.getInvoiceDueDay());
                    expenseEntity.setFirstPayment(actualDue.plusMonths(1));
                }else{
                    expenseEntity.setFirstPayment(expenseEntity.getExpenseDate());
                }
                expenseEntity.setLastPayment(expenseEntity.getFirstPayment().plusMonths(expenseEntity.getInstallments() - 1));
                expenseDao.save(expenseEntity);
            }
        });
    }
}
