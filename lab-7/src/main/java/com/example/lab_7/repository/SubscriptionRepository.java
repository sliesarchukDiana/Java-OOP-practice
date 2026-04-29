package com.example.lab_7.repository;

import com.example.lab_7.model.Subscription;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SubscriptionRepository {

    private final Map<Integer, Subscription> storage = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(1);

    public List<Subscription> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Subscription> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Subscription save(Subscription subscription) {
        if (subscription.getIdSubscription() == null) {
            subscription.setIdSubscription(currentId.getAndIncrement());
        }
        storage.put(subscription.getIdSubscription(), subscription);
        return subscription;
    }

    public boolean deleteById(int id) {
        return storage.remove(id) != null;
    }
}