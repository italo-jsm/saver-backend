package com.example.demo.api.controller;

import com.example.demo.application.service.InvoiceService;
import com.example.demo.api.dto.CreditCardInvoiceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping()
    public ResponseEntity<CreditCardInvoiceDto> getInvoiceByMonthAndYearAndCreditCard(@RequestParam String creditCard,
                                                                                      @RequestParam int month,
                                                                                      @RequestParam int year) {
        return ResponseEntity.ok(invoiceService.createInvoice(creditCard,month, year));
    }
}
