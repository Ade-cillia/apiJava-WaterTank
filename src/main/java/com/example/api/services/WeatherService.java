package com.example.api.services;

import com.example.api.models.Residence;
import com.example.api.models.Weather;
import com.example.api.repositories.ResidenceRepository;
import com.example.api.repositories.WeatherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WeatherService {
    private WeatherRepository weatherRepository;
    private ResidenceRepository residenceRepository;

    public WeatherService(WeatherRepository weatherRepository,ResidenceRepository residenceRepository) {
        this.weatherRepository = weatherRepository;
        this.residenceRepository = residenceRepository;
    }

    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }

    public List<Weather> findTop5ByDate() {
        return weatherRepository.findFirst5ByOrderByDateDesc();

    }
    public Weather findById(Long weatherId) {
        Weather tankData = weatherRepository.findById(weatherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather not found with id: " + weatherId));
        return tankData;
    }

    public Weather createWeather(Long residenceId, Weather weatherDetails) {
        Residence residence = residenceRepository.findById(residenceId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId));
        weatherDetails.setResidence(residence);
        return weatherRepository.save(weatherDetails);
    }

    public Weather updateWeather(Long weatherId, Weather tankDataDetails) {
        Weather tankData = weatherRepository.findById(weatherId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather not found with id: " + weatherId));
        tankDataDetails.setId(tankData.getId());
        return weatherRepository.save(tankDataDetails);
    }

    public void deleteTankDataById(Long weatherId) {
        if (!weatherRepository.existsById(weatherId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather not found with id: " + weatherId);
        }
        weatherRepository.deleteById(weatherId);
    }
}

