package com.example.demo.domain.model.events;

import com.example.demo.domain.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseCreatedEvent {
    private Expense expense;
}
