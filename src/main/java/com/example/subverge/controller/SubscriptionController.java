package com.example.subverge.controller;

import com.example.subverge.dto.response.SubscriptionResponse;
import com.example.subverge.mapper.SubscriptionMapper;
import com.example.subverge.service.SubscriptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionController(SubscriptionService subscriptionService, SubscriptionMapper subscriptionMapper) {
        this.subscriptionService = subscriptionService;
        this.subscriptionMapper = subscriptionMapper;
    }

    @GetMapping("/top")
    public List<SubscriptionResponse> getTopSubscriptions() {
        var topSubscriptions = subscriptionService.getTopSubscriptions();
        return subscriptionMapper.toDtoList(topSubscriptions);
    }

}
