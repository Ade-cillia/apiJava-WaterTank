package com.example.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "utilisateur", uniqueConstraints= {@UniqueConstraint(columnNames={"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull(message = "The lastName field cannot be null")
    @NotBlank(message = "The lastName field cannot be empty")
    @Column(name = "nom")
    private String lastName;

    @NotNull(message = "The firstName field cannot be null")
    @NotBlank(message = "The firstName field cannot be empty")
    @Column(name = "prenom")
    private String firstName;

    @NotNull(message = "The email field cannot be null")
    @NotBlank(message = "The email field cannot be empty")
    @Email(message = "The email must be a valid email address")
    @Column(name = "email")
    private String email;

    @NotNull(message = "The password field cannot be null")
    @NotBlank(message = "The password field cannot be empty")
    @Size(min = 8, message = "The password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$",
            message = "Password must have at least one digit, one lowercase letter, one uppercase letter, and one special character")
    @Column(name = "mdp")
    private String password;

    @NotNull(message = "The phone field cannot be null")
    @NotBlank(message = "The phone field cannot be empty")
    @Column(name = "telephone")
    private String phone;

    @NotNull(message = "The portable field cannot be null")
    @NotBlank(message = "The portable field cannot be empty")
    @Column(name = "portable")
    private String portable;

    @NotNull(message = "The active field cannot be null")
    @Column(name = "actif")
    private boolean active = true;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Residence> residences;

    public User(){}
    public User(Long id, @NotNull(message = "The lastName field cannot be null") String lastName, @NotNull(message = "The firstName field cannot be null") String firstName, @NotNull(message = "The email field cannot be null") String email, @NotNull(message = "The password field cannot be null") String password, @NotNull(message = "The phone field cannot be null") String phone, @NotNull(message = "The portable field cannot be null") String portable, @NotNull(message = "The active field cannot be null") boolean active) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
