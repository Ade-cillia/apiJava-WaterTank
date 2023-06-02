package com.example.api.dto;

import com.example.api.models.Residence;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UserDTO {

    private Long id;

    @NotNull(message = "The lastName field cannot be null")
    @NotBlank(message = "The lastName field cannot be empty")
    private String lastName;

    @NotNull(message = "The firstName field cannot be null")
    @NotBlank(message = "The firstName field cannot be empty")
    private String firstName;

    @NotNull(message = "The email field cannot be null")
    @NotBlank(message = "The email field cannot be empty")
    @Email(message = "The email must be a valid email address")
    private String email;

    @NotNull(message = "The phone field cannot be null")
    @NotBlank(message = "The phone field cannot be empty")
    private String phone;

    @NotNull(message = "The portable field cannot be null")
    @NotBlank(message = "The portable field cannot be empty")
    private String portable;

    @NotNull(message = "The active field cannot be null")
    private boolean active = true;

    private List<Residence> residences;

    public UserDTO() {}
    public UserDTO(Long id, @NotNull(message = "The lastName field cannot be null") String lastName, @NotNull(message = "The firstName field cannot be null") String firstName, @NotNull(message = "The email field cannot be null") String email, @NotNull(message = "The phone field cannot be null") String phone, @NotNull(message = "The portable field cannot be null") String portable, @NotNull(message = "The active field cannot be null") boolean active) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.portable = portable;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPortable() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Residence> getResidences() {
        return residences;
    }
    public void setResidences(List<Residence> residences) {
        this.residences = residences;
    }

}