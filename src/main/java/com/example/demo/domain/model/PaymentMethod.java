package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {
    private String id;
    private String name;
    private Integer invoiceClosingDay = 0;
    private Integer invoiceDueDay = 0;
    private boolean businessDayClosing;

    private LocalDate calculateInvoiceFirstDay(YearMonth yearMonth) {
        if(invoiceClosingDay == 31 || invoiceClosingDay == 30 || invoiceClosingDay == 29){
            invoiceClosingDay = yearMonth.minusMonths(1).atEndOfMonth().getDayOfMonth();
        }
        var closingDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), invoiceDueDay);
        while(closingDate.getDayOfMonth() != invoiceClosingDay){
            closingDate = closingDate.minusDays(1);
        }
        if(businessDayClosing){
            while(!isDiaUtil(closingDate)){
                closingDate = closingDate.minusDays(1);
            }
        }
        return closingDate.plusDays(1);
    }

    public LocalDate firstPaymentDate(LocalDate expenseDate){
        if(this.isCreditCard()){
            LocalDate invoiceFirstDay = calculateInvoiceFirstDay(YearMonth.of(expenseDate.getYear(), expenseDate.getMonth()));
            LocalDate dueDay = LocalDate.of(expenseDate.getYear(), expenseDate.getMonth(), invoiceDueDay);
            if(expenseDate.isAfter(invoiceFirstDay.minusDays(1))){
                return dueDay.plusMonths(1);
            }else return dueDay;
        }else return expenseDate;
    }

    private static boolean isDiaUtil(LocalDate data) {
        return !(data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY) || isHoliday(data);
    }

    private static boolean isHoliday(LocalDate data){
        List<MonthDay> holidays = Arrays.asList(
                MonthDay.of(Month.JANUARY, 1),
                MonthDay.of(Month.APRIL, 18),
                MonthDay.of(Month.APRIL, 21),
                MonthDay.of(Month.MAY, 1),
                MonthDay.of(Month.SEPTEMBER, 7),
                MonthDay.of(Month.OCTOBER, 12),
                MonthDay.of(Month.NOVEMBER, 2),
                MonthDay.of(Month.NOVEMBER, 15),
                MonthDay.of(Month.NOVEMBER, 20),
                MonthDay.of(Month.DECEMBER, 25)
        );
        return holidays.contains(MonthDay.of(data.getMonth(), data.getDayOfMonth()));
    }

    public boolean getBusinessDayClosing() {
        return this.businessDayClosing;
    }

    public boolean isCreditCard(){
        if(this.invoiceClosingDay == null){
            return false;
        }else return this.invoiceClosingDay != 0;
    }
}
