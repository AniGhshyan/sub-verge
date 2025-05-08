package com.example.subverge.service;

import com.example.subverge.dto.request.SubscriptionRequest;
import com.example.subverge.dto.request.UserRequest;
import com.example.subverge.entity.Subscription;
import com.example.subverge.entity.User;

import java.util.List;

public interface UserService {

    User save(UserRequest request);

    User getUserById(Long id);

    User updateUserById(Long id, UserRequest request);

    void deleteById(Long id);

    User addSubscriptionToUser(Long id, SubscriptionRequest subscription);

    List<Subscription> getUserSubscriptions(Long userId);

    void deleteUserSubscription(Long userId, Long subscriptionId);
}
