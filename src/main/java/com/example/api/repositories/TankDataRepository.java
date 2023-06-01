package com.example.api.repositories;

import com.example.api.models.Tank;
import com.example.api.models.TankData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TankDataRepository extends CrudRepository<TankData, Long> {
    @Override
    List<TankData> findAll();
}
