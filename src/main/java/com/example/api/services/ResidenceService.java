package com.example.api.services;

import com.example.api.dto.RegisterDTO;
import com.example.api.models.Residence;
import com.example.api.models.User;
import com.example.api.models.Weather;
import com.example.api.repositories.ResidenceRepository;
import com.example.api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ResidenceService {
    private ResidenceRepository residenceRepository;
    private UserRepository userRepository;

    public ResidenceService(ResidenceRepository residenceRepository,UserRepository userRepository) {
        this.residenceRepository = residenceRepository;
        this.userRepository = userRepository;
    }

    public List<Residence> findAll() {
        return residenceRepository.findAll();
    }
    public Residence findById(Long residenceId) {

        Residence residence = residenceRepository.findById(residenceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId));
        return residence;
    }
    public List<Residence> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
        return residenceRepository.findAllByUser(user);
    }

    public Residence saveResidence(Long userId, Residence residence) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
        residence.setUser(user);
        return residenceRepository.save(residence);
    }

    public Residence updateResidence(Long residenceId, Residence residenceDetails) {
        Residence residence = residenceRepository.findById(residenceId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId));
        residenceDetails.setId(residence.getId());
        residenceRepository.save(residenceDetails);
        return residenceDetails;
    }

    public void deleteResidenceById(Long residenceId) {
        if (!residenceRepository.existsById(residenceId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId);
        }
        residenceRepository.deleteById(residenceId);
    }
}

