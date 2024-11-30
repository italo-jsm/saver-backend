package com.example.demo.unit;

import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.Invoice;
import com.example.demo.domain.model.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvoiceTest {

    @Test
    void asdasd(){
        Invoice invoice = new Invoice(createExpenses(10));
        BigDecimal v = invoice.getInvoiceProjectionByPaymentMethod(LocalDate.now(), new PaymentMethod("", "pm1"));
        System.out.println();
    }

    List<Expense> createExpenses(int number){
        List<Expense> expenses = new ArrayList<>();
        PaymentMethod paymentMethod1 = new PaymentMethod("", "pm1");
        PaymentMethod paymentMethod2 = new PaymentMethod("", "pm2");
        PaymentMethod paymentMethod3 = new PaymentMethod("", "pm3");
        PaymentMethod paymentMethod4 = new PaymentMethod("", "pm4");

        Category category1 = new Category("", "cat1");
        Category category2 = new Category("", "cat2");
        Category category3 = new Category("", "cat3");
        Category category4 = new Category("", "cat4");

        Expense expense1 = new Expense("", LocalDate.now(), BigDecimal.valueOf(11.0), "desc", "ecName", 1, paymentMethod1, category1);
        Expense expense2 = new Expense("", LocalDate.now(), BigDecimal.valueOf(12.0), "desc", "ecName", 1, paymentMethod2, category2);
        Expense expense3 = new Expense("", LocalDate.now(), BigDecimal.valueOf(13.0), "desc", "ecName", 1, paymentMethod3, category3);
        Expense expense4 = new Expense("", LocalDate.now(), BigDecimal.valueOf(14.0), "desc", "ecName", 1, paymentMethod4, category4);

        expenses.add(expense1);
        expenses.add(expense2);
        expenses.add(expense3);
        expenses.add(expense4);

        return expenses;
    }

}
