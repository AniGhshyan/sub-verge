package com.example.subverge.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SubscriptionResponse(
        Long id,
        String serviceName,
        LocalDate startDate,
        LocalDate endDate,
        Boolean isActive
) {
}
