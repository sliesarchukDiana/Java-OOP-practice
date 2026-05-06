package com.example.lab_8.mapper;

import com.example.lab_8.dto.MaterialDto;
import com.example.lab_8.model.Author;
import com.example.lab_8.model.Keyword;
import com.example.lab_8.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    @Mapping(source = "idMaterial", target = "id")
    @Mapping(source = "section.idSection", target = "sectionId")
    @Mapping(source = "authors", target = "authorIds", qualifiedByName = "authorsToIds")
    @Mapping(source = "keywords", target = "keywordIds", qualifiedByName = "keywordsToIds")
    MaterialDto toDto(Material material);

    @Mapping(source = "id", target = "idMaterial")
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "keywords", ignore = true)
    Material toEntity(MaterialDto dto);

    @Named("authorsToIds")
    default Set<Integer> authorsToIds(Set<Author> authors) {
        if (authors == null) return null;
        return authors.stream().map(Author::getIdPerson).collect(Collectors.toSet());
    }

    @Named("keywordsToIds")
    default Set<Integer> keywordsToIds(Set<Keyword> keywords) {
        if (keywords == null) return null;
        return keywords.stream().map(Keyword::getIdKeyword).collect(Collectors.toSet());
    }
}