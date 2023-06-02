package com.example.api.controllers;

import com.example.api.models.Tank;
import com.example.api.models.TankData;
import com.example.api.services.ResidenceService;
import com.example.api.services.TankDataService;
import com.example.api.services.TankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/tank-data")
public class TankDataController {
    private TankDataService tankDataService;

    public TankDataController(TankDataService tankDataService) {
        this.tankDataService = tankDataService;
    }

    @GetMapping(value = "/")
    public List<TankData> getAllTankData() {
        return tankDataService.findAll();
    }


    @GetMapping(value = "/{tankDataId}")
    public TankData findTankDataById(@PathVariable Long tankDataId) {
        return tankDataService.findById(tankDataId);
    }

    @GetMapping("/tank/{tankId}")
    public List<TankData> findAllTankDataByTank(@PathVariable Long tankId) {
        return tankDataService.findAllByTankId(tankId);
    }

    @PostMapping(value = "/tank/{tankId}")
    public ResponseEntity<TankData> createTankData(@PathVariable Long tankId, @Valid @RequestBody TankData tankDataDetails) {
        TankData createdTankData = tankDataService.createTankData(tankId, tankDataDetails);
        return ResponseEntity.ok().body(createdTankData);
    }

    @PutMapping("/{tankDataId}")
    public ResponseEntity<TankData> updateTankData(@PathVariable Long tankDataId, @Valid @RequestBody TankData tankDataDetails) {
        TankData updatedTank = tankDataService.updateTankData(tankDataId, tankDataDetails);
        return ResponseEntity.ok().body(updatedTank);
    }

    @DeleteMapping(value = "/{tankDataId}")
    public ResponseEntity<String> deleteTankDataById(@PathVariable Long tankDataId) {
        tankDataService.deleteTankDataById(tankDataId);
        return ResponseEntity.ok("TankData successfully deleted");
    }
}
