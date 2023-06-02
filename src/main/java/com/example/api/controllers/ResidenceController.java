package com.example.api.controllers;

import com.example.api.dto.LoginDTO;
import com.example.api.dto.RegisterDTO;
import com.example.api.dto.UserDTO;
import com.example.api.models.Residence;
import com.example.api.models.User;
import com.example.api.models.Weather;
import com.example.api.services.ResidenceService;
import com.example.api.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/residences")
public class ResidenceController {
    private ResidenceService residenceService;

    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @GetMapping(value = "/")
    public List<Residence> getAllResidences() {
        return residenceService.findAll();
    }

    @GetMapping(value = "/{residenceId}")
    public Residence findById(@PathVariable Long residenceId) {
        Residence residence = residenceService.findById(residenceId);
        return residence;
    }

    @GetMapping(value = "/user/{userId}")
    public List<Residence> findResidencesByUser(@PathVariable Long userId) {
        List<Residence> residences = residenceService.findAllByUserId(userId);
        return residences;
    }

    @PostMapping(value = "/user/{userId}")
    public ResponseEntity<Residence> createResidence(@PathVariable Long userId, @Valid @RequestBody Residence residence) {
        Residence createdResidence = residenceService.saveResidence(userId,residence);
        return ResponseEntity.ok().body(createdResidence);
    }

    @PutMapping("/{residenceId}")
    public ResponseEntity<Residence> updateResidence(@PathVariable Long residenceId, @RequestBody Residence residenceDetails) {
        Residence updatedResidence = residenceService.updateResidence(residenceId, residenceDetails);
        return ResponseEntity.ok().body(updatedResidence);
    }

    @DeleteMapping(value = "/{residenceId}")
    public ResponseEntity<String> deleteResidenceById(@PathVariable Long residenceId) {
        residenceService.deleteResidenceById(residenceId);
        return ResponseEntity.ok("Tank successfully deleted");
    }
}
