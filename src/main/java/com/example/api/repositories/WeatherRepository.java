package com.example.api.repositories;

import com.example.api.models.User;
import com.example.api.models.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherRepository extends CrudRepository<Weather, Long> {
    @Override
    List<Weather> findAll();

    List<Weather> findFirst5ByOrderByDateDesc();
}
