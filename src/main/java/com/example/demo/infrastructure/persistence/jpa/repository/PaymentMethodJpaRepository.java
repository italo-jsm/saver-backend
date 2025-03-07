package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.repository.PaymentMethodRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.PaymentMethodDao;
import com.example.demo.infrastructure.persistence.jpa.entity.PaymentMethodEntity;
import com.example.demo.infrastructure.persistence.jpa.mapper.PaymentMethodEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class PaymentMethodJpaRepository implements PaymentMethodRepository {

    private final PaymentMethodDao paymentMethodDao;
    private final PaymentMethodEntityMapper paymentMethodEntityMapper;

    @Override
    public List<PaymentMethod> findAll() {
        List<PaymentMethodEntity> all = paymentMethodDao.findAll();
        return all.stream().map(paymentMethodEntityMapper::toDomain).toList();
    }

    @Override
    public String insert(PaymentMethod paymentMethod) {
        return paymentMethodDao.save(paymentMethodEntityMapper.toEntity(paymentMethod)).getId();
    }

    @Override
    public Optional<PaymentMethod> findById(String id) {
        Optional<PaymentMethodEntity> byId = paymentMethodDao.findById(id);
        return byId.map(paymentMethodEntityMapper::toDomain);
    }
}
