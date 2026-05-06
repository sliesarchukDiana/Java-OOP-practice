package com.example.lab_8.mapper;

import com.example.lab_8.dto.AuthorDto;
import com.example.lab_8.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(source = "idPerson", target = "id")
    AuthorDto toDto(Author author);

    @Mapping(source = "id", target = "idPerson")
    Author toEntity(AuthorDto authorDto);

    @Mapping(source = "id", target = "idPerson")
    void updateEntityFromDto(AuthorDto authorDto, @MappingTarget Author author);
}