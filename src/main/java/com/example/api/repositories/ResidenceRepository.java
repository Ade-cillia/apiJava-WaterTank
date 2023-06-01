package com.example.api.repositories;

import com.example.api.models.Residence;
import com.example.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResidenceRepository extends CrudRepository<Residence, Long> {
    @Override
    List<Residence> findAll();
}
