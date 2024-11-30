package com.example.demo.application.service;

import com.example.demo.api.dto.PaymentMethodDto;
import com.example.demo.application.mapper.PaymentMethodMapper;
import com.example.demo.domain.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    public String createPaymentMethod(PaymentMethodDto paymentMethodDto){
        return paymentMethodRepository.insert(paymentMethodMapper.toDomain(paymentMethodDto));
    }

    public List<PaymentMethodDto> getAll(){
        return paymentMethodRepository.findAll().stream().map(paymentMethodMapper::toDto).toList();
    }
}
