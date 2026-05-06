package com.example.lab_8.mapper;

import com.example.lab_8.dto.ClientDto;
import com.example.lab_8.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "idPerson", target = "id")
    ClientDto toDto(Client client);

    @Mapping(source = "id", target = "idPerson")
    Client toEntity(ClientDto clientDto);
}