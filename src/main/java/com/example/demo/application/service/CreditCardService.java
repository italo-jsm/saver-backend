package com.example.demo.application.service;

import com.example.demo.api.dto.CreditCardDto;
import com.example.demo.application.mapper.CreditCardMapper;
import com.example.demo.domain.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;

    public String createCreditCard(CreditCardDto creditCardDto){
        return creditCardRepository.insert(creditCardMapper.toDomain(creditCardDto));
    }

    public List<CreditCardDto> getAll(){
        return creditCardRepository.findAll().stream().map(creditCardMapper::toDto).toList();
    }
}
