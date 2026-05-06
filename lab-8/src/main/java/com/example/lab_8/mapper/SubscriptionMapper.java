package com.example.lab_8.mapper;

import com.example.lab_8.dto.SubscriptionDto;
import com.example.lab_8.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(source = "idSubscription", target = "id")
    @Mapping(source = "client.idPerson", target = "clientId")
    @Mapping(source = "section.idSection", target = "sectionId")
    @Mapping(source = "keyword.idKeyword", target = "keywordId")
    SubscriptionDto toDto(Subscription subscription);

    @Mapping(source = "id", target = "idSubscription")
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "keyword", ignore = true)
    Subscription toEntity(SubscriptionDto dto);
}