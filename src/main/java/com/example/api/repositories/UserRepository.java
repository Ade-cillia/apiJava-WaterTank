package com.example.api.repositories;

import com.example.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    User findByEmail(String email);
    boolean existsByEmail(String email);
}
