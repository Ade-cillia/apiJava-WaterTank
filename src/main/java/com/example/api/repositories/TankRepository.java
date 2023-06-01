package com.example.api.repositories;

import com.example.api.models.Tank;
import com.example.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TankRepository extends CrudRepository<Tank, Long> {
    @Override
    List<Tank> findAll();
}
