package com.example.subverge.mapper;

import com.example.subverge.dto.request.SubscriptionRequest;
import com.example.subverge.dto.response.SubscriptionResponse;
import com.example.subverge.entity.Subscription;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SubscriptionMapper {

    public abstract Subscription mapToEntity(SubscriptionRequest request);

    public abstract SubscriptionResponse toResponse(Subscription subscription);

    public abstract List<SubscriptionResponse> toDtoList(List<Subscription> subscriptions);


}
