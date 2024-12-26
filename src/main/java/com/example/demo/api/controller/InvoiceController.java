package com.example.demo.api.controller;

import com.example.demo.application.service.InvoiceService;
import com.example.demo.api.dto.CreditCardInvoiceDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping()
    public ResponseEntity<CreditCardInvoiceDto> getInvoiceByMonthAndYearAndCreditCard(@AuthenticationPrincipal UserDetails userDetails,
                                                                                      @RequestParam String creditCardId,
                                                                                      @RequestParam int month,
                                                                                      @RequestParam int year) {

        return ResponseEntity.ok(invoiceService.createInvoice(creditCardId, month, year));
    }
}
