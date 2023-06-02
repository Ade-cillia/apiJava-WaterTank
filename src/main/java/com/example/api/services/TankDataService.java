package com.example.api.services;

import com.example.api.models.Residence;
import com.example.api.models.Tank;
import com.example.api.models.TankData;
import com.example.api.repositories.ResidenceRepository;
import com.example.api.repositories.TankDataRepository;
import com.example.api.repositories.TankRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TankDataService {
    private TankDataRepository tankDataRepository;
    private TankRepository tankRepository;


    public TankDataService(TankDataRepository tankDataRepository, TankRepository tankRepository) {
        this.tankDataRepository = tankDataRepository;
        this.tankRepository = tankRepository;
    }

    public List<TankData> findAll() {
        return tankDataRepository.findAll();
    }

    public TankData findById(Long tankDataId) {
        TankData tankData = tankDataRepository.findById(tankDataId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TankData not found with id: " + tankDataId));
        return tankData;
    }

    public List<TankData> findAllByTankId(Long tankId) {
        Tank tank = tankRepository.findById(tankId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tank not found with id: " + tankId));
        return tankDataRepository.findAllByTank(tank);
    }

    public TankData createTankData(Long tankId, TankData tankDataDetails) {
        Tank tank = tankRepository.findById(tankId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tank not found with id: " + tankId));
        tankDataDetails.setTank(tank);
        return tankDataRepository.save(tankDataDetails);
    }

    public TankData updateTankData(Long tankDataId, TankData tankDataDetails) {
        TankData tankData = tankDataRepository.findById(tankDataId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TankData not found with id: " + tankDataId));
        tankDataDetails.setId(tankData.getId());
        return tankDataRepository.save(tankDataDetails);
    }

    public void deleteTankDataById(Long tankDataId) {
        if (!tankDataRepository.existsById(tankDataId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TankData not found with id: " + tankDataId);
        }
        tankDataRepository.deleteById(tankDataId);
    }
}

