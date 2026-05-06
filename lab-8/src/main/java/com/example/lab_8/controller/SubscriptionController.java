package com.example.lab_8.controller;

import com.example.lab_8.dto.SubscriptionDto;
import com.example.lab_8.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping
    public List<SubscriptionDto> getAll() {
        return subscriptionService.getAllSubscriptions();
    }

    @PostMapping
    public SubscriptionDto create(@Valid @RequestBody SubscriptionDto dto) {
        return subscriptionService.createSubscription(dto);
    }
}