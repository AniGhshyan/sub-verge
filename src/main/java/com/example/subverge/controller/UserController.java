package com.example.subverge.controller;

import com.example.subverge.dto.request.SubscriptionRequest;
import com.example.subverge.dto.request.UserRequest;
import com.example.subverge.dto.response.SubscriptionResponse;
import com.example.subverge.dto.response.UserResponse;
import com.example.subverge.mapper.SubscriptionMapper;
import com.example.subverge.mapper.UserMapper;
import com.example.subverge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;

    public UserController(UserService userService, UserMapper userMapper, SubscriptionMapper subscriptionMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.subscriptionMapper = subscriptionMapper;
    }

    @PostMapping
    public UserResponse save(@Valid @RequestBody UserRequest request) {
        var savedUser = userService.save(request);
        return userMapper.toResponse(savedUser);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        var userById = userService.getUserById(id);
        return userMapper.toResponse(userById);
    }

    @PutMapping("/{id}")
    public UserResponse updateById(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        var user = userService.updateUserById(id, request);
        return userMapper.toResponse(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/{id}/subscriptions")
    public UserResponse addSubscriptionToUser(@PathVariable Long id, @Valid @RequestBody SubscriptionRequest subscription) {
        var user = userService.addSubscriptionToUser(id, subscription);
        return userMapper.toResponse(user);
    }

    @GetMapping("/{id}/subscriptions")
    public List<SubscriptionResponse> getUserSubscriptions(@PathVariable Long id) {
        var userSubscriptions = userService.getUserSubscriptions(id);
        return subscriptionMapper.toDtoList(userSubscriptions);
    }

    @DeleteMapping("/{id}/subscriptions/{subscriptionId}")
    public void deleteUserSubscription(@PathVariable Long id, @PathVariable Long subscriptionId) {
        userService.deleteUserSubscription(id, subscriptionId);
    }

}
