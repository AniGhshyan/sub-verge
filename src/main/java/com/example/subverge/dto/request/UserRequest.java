package com.example.subverge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record UserRequest(
        @NotBlank(message = "User's name can't be null or empty")
        String name,
        @NotBlank(message = "User's surname can't be null or empty")
        String surname,
        @Email(message = "Please provide a valid email address")
        String email,
        LocalDate dateOfBirth,
        LocalDateTime createdAt

) {
}
