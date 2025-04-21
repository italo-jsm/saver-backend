package com.example.demo.domain.model.events;

import com.example.demo.domain.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseCreatedEvent {
    public enum UPDATE_EVENT{CREATE, DELETE}
    private Expense expense;
    private UPDATE_EVENT updateEvent;
}
