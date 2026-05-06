package com.example.lab_8.service;

import com.example.lab_8.dto.SubscriptionDto;
import com.example.lab_8.mapper.SubscriptionMapper;
import com.example.lab_8.model.Subscription;
import com.example.lab_8.repository.ClientRepository;
import com.example.lab_8.repository.SubscriptionRepository;
import com.example.lab_8.repository.CatalogueSectionRepository;
import com.example.lab_8.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ClientRepository clientRepository;
    private final CatalogueSectionRepository sectionRepository;
    private final KeywordRepository keywordRepository;
    private final SubscriptionMapper subscriptionMapper;

    public List<SubscriptionDto> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(subscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SubscriptionDto createSubscription(SubscriptionDto dto) {
        Subscription sub = subscriptionMapper.toEntity(dto);

        sub.setClient(clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found")));

        if (dto.getSectionId() != null) {
            sub.setSection(sectionRepository.findById(dto.getSectionId()).orElse(null));
        }

        if (dto.getKeywordId() != null) {
            sub.setKeyword(keywordRepository.findById(dto.getKeywordId()).orElse(null));
        }

        return subscriptionMapper.toDto(subscriptionRepository.save(sub));
    }
}