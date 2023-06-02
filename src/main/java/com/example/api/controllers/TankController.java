package com.example.api.controllers;

import com.example.api.models.Residence;
import com.example.api.models.Tank;
import com.example.api.models.User;
import com.example.api.services.ResidenceService;
import com.example.api.services.TankService;
import com.example.api.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/tanks")
public class TankController {
    private TankService tankService;
    private ResidenceService residenceService;

    public TankController(TankService tankService, ResidenceService residenceService) {
        this.tankService = tankService;
        this.residenceService = residenceService;
    }

    @GetMapping(value = "/")
    public List<Tank> getAllTanks() {
        return tankService.findAll();
    }


    @GetMapping(value = "/{tankId}")
    public Tank findTankById(@PathVariable Long tankId) {
        return tankService.findById(tankId);
    }

    @GetMapping("/residence/{residenceId}")
    public List<Tank> findAllTanksByResidence(@PathVariable Long residenceId) {
        return tankService.findAllByResidence(residenceId);
    }

    @PostMapping(value = "/residence/{residenceId}")
    public ResponseEntity<Tank> createTank(@PathVariable Long residenceId, @Valid @RequestBody Tank tankDetails) {
        Tank createdTank = tankService.createTank(residenceId, tankDetails);
        return ResponseEntity.ok().body(createdTank);
    }

    @PutMapping("/{tankId}")
    public ResponseEntity<Tank> updateTank(@PathVariable Long tankId, @RequestBody Tank tankDetails) {
        Tank updatedTank = tankService.updateTank(tankId, tankDetails);
        return ResponseEntity.ok().body(updatedTank);
    }

    @DeleteMapping(value = "/{tankId}")
    public ResponseEntity<String> deleteTankById(@PathVariable Long tankId) {
        tankService.deleteTankById(tankId);
        return ResponseEntity.ok("Tank successfully deleted");
    }
}
