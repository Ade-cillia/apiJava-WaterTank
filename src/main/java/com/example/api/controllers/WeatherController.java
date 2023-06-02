package com.example.api.controllers;

import com.example.api.models.Weather;
import com.example.api.services.WeatherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/weathers")
public class WeatherController {
    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/")
    public List<Weather> getAllWeather() {
        return weatherService.findAll();
    }

    @GetMapping(value = "/lasts")
    public List<Weather> findTop5ByDate() {
        return weatherService.findTop5ByDate();
    }

    @GetMapping(value = "/{weatherId}")
    public Weather findById(@PathVariable Long weatherId) {
        return weatherService.findById(weatherId);
    }

    @PostMapping(value = "/residence/{residenceId}")
    public ResponseEntity<Weather> createWeather(@PathVariable Long residenceId, @Valid @RequestBody Weather tankDataDetails) {
        Weather createdTankData = weatherService.createWeather(residenceId, tankDataDetails);
        return ResponseEntity.ok().body(createdTankData);
    }

    @PutMapping("/{weatherId}")
    public ResponseEntity<Weather> updateWeather(@PathVariable Long weatherId, @Valid @RequestBody Weather tankDataDetails) {
        Weather updatedTank = weatherService.updateWeather(weatherId, tankDataDetails);
        return ResponseEntity.ok().body(updatedTank);
    }
}
