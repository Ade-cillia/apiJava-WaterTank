package com.example.api.controllers;

import com.example.api.dto.LoginDTO;
import com.example.api.dto.RegisterDTO;
import com.example.api.dto.UserDTO;
import com.example.api.models.Residence;
import com.example.api.models.User;
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
    private UserService userService;
    private ModelMapper modelMapper;

    public ResidenceController(ResidenceService residenceService,UserService userService,ModelMapper modelMapper) {
        this.residenceService = residenceService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/")
    public List<Residence> getAllResidences() {
        return residenceService.findAll();
    }

    @GetMapping(value = "/{residenceId}")
    public Residence findUserById(@PathVariable(name = "residenceId", required = false) Residence residence) {
        if (residence == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not Found");
        }
        return residence;
    }

    @GetMapping(value = "/user/{userId}")
    public List<Residence> findResidencesByUser(@PathVariable(name = "userId", required = false) User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not Found");
        }
        return residenceService.findAllByUser(user);
    }

    @PostMapping(value = "/user/{userId}")
    public ResponseEntity<Residence> createResidence(@PathVariable Long userId, @Valid @RequestBody Residence residence) {
        User user = userService.findUserById(userId);
        residence.setUser(user);
        Residence createdResidence = residenceService.saveResidence(residence);
        return ResponseEntity.ok().body(createdResidence);
    }

    @PutMapping("/{residenceId}")
    public ResponseEntity<Residence> updateResidence(@PathVariable Long residenceId, @RequestBody Residence residenceDetails) {
        Residence updatedResidence = residenceService.updateResidence(residenceId, residenceDetails);
        return ResponseEntity.ok().body(updatedResidence);
    }

    @DeleteMapping(value = "/{residenceId}")
    public ResponseEntity<String> deleteResidenceById(@PathVariable Long residenceId) {
        try {
            residenceService.deleteResidenceById(residenceId);
            return ResponseEntity.ok("Residence successfully deleted");
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId);
        }
    }
}
