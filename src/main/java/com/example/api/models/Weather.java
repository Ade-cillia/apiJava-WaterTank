package com.example.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "temps")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "The weather field cannot be null")
    @Column(name = "temps")
    private String weather;

    @NotNull(message = "The weatherMin field cannot be null")
    @Column(name = "temperatureMin")
    private double weatherMin;

    @NotNull(message = "The weatherMax field cannot be null")
    @Column(name = "temperatureMax")
    private double weatherMax;

    @NotNull(message = "The date field cannot be null")
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Residence residence;

    public Weather(){}
    public Weather(Long id, @NotNull(message = "The weather field cannot be null") String weather, @NotNull(message = "The weatherMin field cannot be null") double weatherMin, @NotNull(message = "The weatherMax field cannot be null") double weatherMax, @NotNull(message = "The date field cannot be null") LocalDate date) {
        this.id = id;
        this.weather = weather;
        this.weatherMin = weatherMin;
        this.weatherMax = weatherMax;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getWeatherMin() {
        return weatherMin;
    }

    public void setWeatherMin(double weatherMin) {
        this.weatherMin = weatherMin;
    }

    public double getWeatherMax() {
        return weatherMax;
    }

    public void setWeatherMax(double weatherMax) {
        this.weatherMax = weatherMax;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Residence getResidence() {
        return residence;
    }

}
