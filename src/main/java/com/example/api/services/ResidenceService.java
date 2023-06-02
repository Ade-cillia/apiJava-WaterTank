package com.example.api.services;

import com.example.api.dto.RegisterDTO;
import com.example.api.models.Residence;
import com.example.api.models.User;
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
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder passwordEncoder;

    public ResidenceService(ResidenceRepository residenceRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.residenceRepository = residenceRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Residence> findAll() {
        return residenceRepository.findAll();
    }
    public List<Residence> findAllByUser(User user) {
        return residenceRepository.findAllByUser(user);
    }

    public Residence saveResidence(Residence residence) {
        return residenceRepository.save(residence);
    }

    public Residence updateResidence(Long residenceId, Residence residenceDetails) {
        return residenceRepository.findById(residenceId)
                .map(residence -> {
                    residence.setNbPerson(residenceDetails.getNbPerson());
                    residence.setType(residenceDetails.getType());
                    residence.setPrincipal(residenceDetails.isPrincipal());
                    residence.setAddress(residenceDetails.getAddress());
                    residence.setPostalCode(residenceDetails.getPostalCode());
                    residence.setLat(residenceDetails.getLat());
                    residence.setLon(residenceDetails.getLon());
                    return residenceRepository.save(residence);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId));
    }

    public void deleteResidenceById(Long residenceId) {
        if (!residenceRepository.existsById(residenceId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Residence not found with id: " + residenceId);
        }
        residenceRepository.deleteById(residenceId);
    }
}

