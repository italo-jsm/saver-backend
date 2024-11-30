package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Refuel;
import com.example.demo.domain.repository.RefuelRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.RefuelDao;
import com.example.demo.infrastructure.persistence.jpa.mapper.RefuelEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class RefuelJpaRepository implements RefuelRepository {

    private RefuelDao refuelDao;
    private RefuelEntityMapper refuelEntityMapper;

    @Override
    public String insert(Refuel refuel) {
        return refuelDao.save(refuelEntityMapper.toEntity(refuel)).getId();
    }

    @Override
    public Optional<Refuel> findRefuelById(String id) {
        return refuelDao.findById(id).map(refuelEntityMapper::toDomain);
    }
}
