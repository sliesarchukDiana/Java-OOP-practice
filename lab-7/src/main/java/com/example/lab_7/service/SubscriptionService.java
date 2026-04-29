package com.example.lab_7.service;

import com.example.lab_7.model.Subscription;
import com.example.lab_7.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(int id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription createSubscription(Subscription subscription) {
        log.info("Creating new subscription for client id {}", subscription.getIdClient());
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> updateSubscription(int id, Subscription subscriptionDetails) {
        log.info("Updating subscription with id {}", id);
        return subscriptionRepository.findById(id).map(existingSubscription -> {
            existingSubscription.setFormatType(subscriptionDetails.getFormatType());
            existingSubscription.setIdClient(subscriptionDetails.getIdClient());
            existingSubscription.setIdSection(subscriptionDetails.getIdSection());
            existingSubscription.setIdKeyword(subscriptionDetails.getIdKeyword());
            return subscriptionRepository.save(existingSubscription);
        });
    }

    public Optional<Subscription> patchSubscription(int id, Subscription subscriptionDetails) {
        log.info("Patching subscription with id {}", id);
        return subscriptionRepository.findById(id).map(existingSubscription -> {
            if (subscriptionDetails.getFormatType() != null) {
                existingSubscription.setFormatType(subscriptionDetails.getFormatType());
            }
            if (subscriptionDetails.getIdClient() != null) {
                existingSubscription.setIdClient(subscriptionDetails.getIdClient());
            }
            if (subscriptionDetails.getIdSection() != null) {
                existingSubscription.setIdSection(subscriptionDetails.getIdSection());
            }
            if (subscriptionDetails.getIdKeyword() != null) {
                existingSubscription.setIdKeyword(subscriptionDetails.getIdKeyword());
            }
            return subscriptionRepository.save(existingSubscription);
        });
    }

    public boolean deleteSubscription(int id) {
        log.info("Deleting subscription with id {}", id);
        return subscriptionRepository.deleteById(id);
    }
}