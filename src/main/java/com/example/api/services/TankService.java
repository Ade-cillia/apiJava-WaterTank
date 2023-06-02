package com.example.api.services;

import com.example.api.models.Residence;
import com.example.api.models.Tank;
import com.example.api.models.User;
import com.example.api.models.Weather;
import com.example.api.repositories.ResidenceRepository;
import com.example.api.repositories.TankRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TankService {
    private TankRepository tankRepository;
    private ResidenceRepository residenceRepository;
    public TankService(TankRepository tankRepository,ResidenceRepository residenceRepository) {
        this.tankRepository = tankRepository;
        this.residenceRepository = residenceRepository;
    }

    public List<Tank> findAll() {
        return tankRepository.findAll();
    }

    public List<Tank> findAllByResidence(Long residenceId) {
        Residence residence = residenceRepository.findById(residenceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId));
        return tankRepository.findAllByResidence(residence);
    }
    public Tank findById(Long tankId) {
        Tank tank = tankRepository.findById(tankId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tank not found with id: " + tankId));
        return tank;
    }

    public Tank createTank(Long residenceId, Tank tankDetails) {
        return residenceRepository.findById(residenceId)
                .map(residence -> {
                    tankDetails.setResidence(residence);
                    return tankRepository.save(tankDetails);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId));
    }

    public Tank updateTank(Long tankId, Tank tankDetails) {
        Tank tank = tankRepository.findById(tankId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tank not found with id: " + tankId));
        tankDetails.setId(tank.getId());
        return tankRepository.save(tankDetails);
    }

    public void deleteTankById(Long tankId) {
        if (!tankRepository.existsById(tankId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tank not found with id: " + tankId);
        }
        tankRepository.deleteById(tankId);
    }
}

