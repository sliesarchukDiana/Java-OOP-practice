package com.example.lab_8.mapper;

import com.example.lab_8.dto.ClientDto;
import com.example.lab_8.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "idPerson", source = "id")
    Client toEntity(ClientDto dto);

    @Mapping(target = "id", source = "idPerson")
    ClientDto toDto(Client client);

    @Mapping(target = "idPerson", ignore = true)
    void updateClientFromDto(ClientDto dto, @MappingTarget Client entity);
}