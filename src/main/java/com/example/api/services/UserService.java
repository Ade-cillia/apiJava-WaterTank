package com.example.api.services;

import com.example.api.dto.RegisterDTO;
import com.example.api.models.User;
import com.example.api.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(RegisterDTO registerDTO) {
        if (existsByEmail(registerDTO.getEmail())) {
            throw new IllegalArgumentException("Email must be unique");
        }

        User user = modelMapper.map(registerDTO, User.class);
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        return null;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setPortable(user.getPortable());

        if (user.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            existingUser.setPassword(encodedPassword);
        }

        return userRepository.save(existingUser);
    }

    public User updatePartialUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getPhone() != null) {
            existingUser.setPhone(user.getPhone());
        }

        if (user.getPortable() != null) {
            existingUser.setPortable(user.getPortable());
        }

        if (user.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            existingUser.setPassword(encodedPassword);
        }

        return userRepository.save(existingUser);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

}

