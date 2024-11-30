package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Batch;
import com.example.demo.domain.repository.BatchRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.BatchDao;
import com.example.demo.infrastructure.persistence.jpa.entity.BatchEntity;
import com.example.demo.infrastructure.persistence.jpa.mapper.BatchEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class BatchJpaRepository implements BatchRepository {

    private final BatchDao batchDao;
    private final BatchEntityMapper batchEntityMapper;

    @Override
    public String insert(Batch batch) {
        BatchEntity entity = batchEntityMapper.toEntity(batch);
        return batchDao.save(entity).getBatchUuid();
    }

    @Override
    public Optional<Batch> findById(String id) {
        return batchDao.findById(id).map(batchEntityMapper::toDomain);
    }
}
