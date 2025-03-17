package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.Income;
import com.example.demo.domain.repository.IncomeRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.IncomeDao;
import com.example.demo.infrastructure.persistence.jpa.mapper.IncomeEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@AllArgsConstructor
@Repository
public class IncomeJpaRepository implements IncomeRepository {

    private final IncomeDao incomeDao;
    private final IncomeEntityMapper incomeEntityMapper;

    @Override
    public String insert(Income income) {
        return incomeDao.save(incomeEntityMapper.toEntity(income)).getId();
    }

    public List<Income> findAllIncomes(int month, int year){
        return incomeDao.findIncomeByMonthAndYear(month, year).stream().map(incomeEntityMapper::toDomain).toList();
    }
}
