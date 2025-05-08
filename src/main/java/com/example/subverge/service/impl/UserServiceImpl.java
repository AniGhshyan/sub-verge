package com.example.subverge.service.impl;

import com.example.subverge.dto.request.SubscriptionRequest;
import com.example.subverge.dto.request.UserRequest;
import com.example.subverge.entity.Subscription;
import com.example.subverge.entity.User;
import com.example.subverge.exception.app.AlreadySubscribedException;
import com.example.subverge.exception.app.SubscriptionNotFoundException;
import com.example.subverge.exception.app.UserAlreadyExistsException;
import com.example.subverge.exception.app.UserNotFoundException;
import com.example.subverge.mapper.SubscriptionMapper;
import com.example.subverge.mapper.UserMapper;
import com.example.subverge.repository.SubscriptionRepository;
import com.example.subverge.repository.UserRepository;
import com.example.subverge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;


    @Override
    public User save(UserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            String message = "User with email " + request.email() + " already exists.";
            log.error(message);
            throw new UserAlreadyExistsException(message);
        }
        var user = userMapper.mapToEntity(request);
        var savedUser = userRepository.save(user);
        log.info("User with email {} has been successfully created", savedUser.getEmail());
        return savedUser;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new UserNotFoundException(id);
                });
    }

    @Override
    public User updateUserById(Long id, UserRequest request) {

        var existingUser = getUserById(id);

        userMapper.updateEntity(existingUser, request);
        var updatedUser = userRepository.save(existingUser);
        log.info("User with id {} has been successfully updated", updatedUser.getId());

        return updatedUser;
    }

    @Override
    public void deleteById(Long id) {
        var user = getUserById(id);
        userRepository.deleteById(user.getId());

        log.info("User with id {} has been successfully deleted", id);
    }

    @Override
    @Transactional
    public User addSubscriptionToUser(Long userId, SubscriptionRequest subscriptionRequest) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        if (isAlreadySubscribed(user, subscriptionRequest.serviceName())) {
            log.warn("User with ID {} is already subscribed to the service: {}", userId, subscriptionRequest.serviceName());
            throw new AlreadySubscribedException("You are already subscribed to this service");
        }
        var byServiceName = subscriptionRepository.findByServiceName(subscriptionRequest.serviceName());
        var newSubscription = byServiceName.orElseGet(() -> createAndSaveSubscription(subscriptionRequest));
        user.getSubscriptions().add(newSubscription);
        log.info("Saving updated user with ID: {}", userId);
        return userRepository.save(user);
    }

    @Override
    public List<Subscription> getUserSubscriptions(Long userId) {
        var user = getUserById(userId);
        var subscriptions = List.copyOf(user.getSubscriptions());
        log.info("User with ID: {} has {} subscriptions", userId, subscriptions.size());
        return subscriptions;
    }

    @Override
    public void deleteUserSubscription(Long userId, Long subscriptionId) {
        var user = getUserById(userId);
        Subscription subscriptionToRemove = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));

        user.getSubscriptions().remove(subscriptionToRemove);

        userRepository.save(user);
    }

    private Subscription createAndSaveSubscription(SubscriptionRequest subscriptionRequest) {
        var subscription = subscriptionMapper.mapToEntity(subscriptionRequest);
        return subscriptionRepository.save(subscription);
    }

    private boolean isAlreadySubscribed(User user, String serviceName) {
        return user.getSubscriptions().stream()
                .anyMatch(subscription -> subscription.getServiceName().equals(serviceName));
    }
}
