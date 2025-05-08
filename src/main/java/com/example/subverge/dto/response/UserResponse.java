package com.example.subverge.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserResponse(
        Long id,
        String name,
        String surname,
        String email,

        LocalDate dateOfBirth,

        LocalDateTime createdAt,
        List<SubscriptionResponse> subscriptions
) {
}
