package com.example.api.controllers;

import com.example.api.dto.LoginDTO;
import com.example.api.dto.RegisterDTO;
import com.example.api.dto.UserDTO;
import com.example.api.models.User;
import com.example.api.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody RegisterDTO registerDTO) {
        User registeredUser = userService.saveUser(registerDTO);
        UserDTO userDTO = modelMapper.map(registeredUser, UserDTO.class);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        User user = userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping(value = "/")
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{userId}")
    public UserDTO findUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return modelMapper.map(user, UserDTO.class);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @Valid @RequestBody RegisterDTO userDTO) {
        User user = userService.findUserById(userId);
        modelMapper.map(userDTO, user);
        User updatedUser = userService.updateUser(user);
        UserDTO updatedUserDTO = modelMapper.map(updatedUser, UserDTO.class);
        return ResponseEntity.ok().body(updatedUserDTO);
    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> updatePartialUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setId(userId);
        User updatedUser = userService.updatePartialUser(userId, user);
        UserDTO updatedUserDTO = modelMapper.map(updatedUser, UserDTO.class);
        return ResponseEntity.ok().body(updatedUserDTO);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User successfully deleted");
    }
}
