package com.example.api.dto;

import jakarta.validation.constraints.*;

public class RegisterDTO {
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

    @NotNull(message = "The password field cannot be null")
    @NotBlank(message = "The password field cannot be empty")
    @Size(min = 8, message = "The password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$",
            message = "Password must have at least one digit, one lowercase letter, one uppercase letter, and one special character")
    private String password;

    @NotNull(message = "The phone field cannot be null")
    @NotBlank(message = "The phone field cannot be empty")
    private String phone;

    @NotNull(message = "The portable field cannot be null")
    @NotBlank(message = "The portable field cannot be empty")
    private String portable;

    public RegisterDTO() {
    }

    public RegisterDTO(@NotNull(message = "The lastName field cannot be null") String lastName, @NotNull(message = "The firstName field cannot be null") String firstName, @NotNull(message = "The email field cannot be null") String email, @NotNull(message = "The password field cannot be null") String password, @NotNull(message = "The phone field cannot be null") String phone, @NotNull(message = "The portable field cannot be null") String portable) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.portable = portable;
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
}
