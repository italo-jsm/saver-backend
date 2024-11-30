package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Vehicle;
import com.example.demo.domain.repository.VehicleRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.VehicleDao;
import com.example.demo.infrastructure.persistence.jpa.mapper.VehicleEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class VehicleJpaRepository implements VehicleRepository {

    private final VehicleDao vehicleDao;
    private final VehicleEntityMapper vehicleEntityMapper;
    @Override
    public List<Vehicle> findAll() {
        return vehicleDao.findAll().stream().map(vehicleEntityMapper::toDomain).toList();
    }

    @Override
    public String insert(Vehicle vehicle) {
        return vehicleDao.save(vehicleEntityMapper.toEntity(vehicle)).getId();
    }
}
