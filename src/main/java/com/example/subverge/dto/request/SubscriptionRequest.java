package com.example.subverge.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SubscriptionRequest(

        @NotEmpty(message = "Service name cannot be empty")
        String serviceName,

        @NotNull(message = "Start date cannot be null")
        @Future(message = "Start date must be in the future")
        LocalDate startDate,

        @NotNull(message = "End date cannot be null")
        @Future(message = "End date must be in the future")
        LocalDate endDate,

        Boolean isActive

) {
}
