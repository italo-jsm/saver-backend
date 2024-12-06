package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.CreditCard;
import com.example.demo.domain.repository.CreditCardRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.CreditCardDao;
import com.example.demo.infrastructure.persistence.jpa.mapper.CreditCardEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CreditCardJpaRepository implements CreditCardRepository {

    private final CreditCardDao creditCardDao;
    private final CreditCardEntityMapper creditCardEntityMapper;

    @Override
    public List<CreditCard> findAll() {
        return creditCardDao.findAll().stream().map(creditCardEntityMapper::toDomain).toList();
    }

    @Override
    public String insert(CreditCard creditCard) {
        return creditCardDao.save(creditCardEntityMapper.toEntity(creditCard)).getId();
    }

    @Override
    public Optional<CreditCard> findById(String id) {
        return creditCardDao.findById(id).map(creditCardEntityMapper::toDomain);
    }
}
