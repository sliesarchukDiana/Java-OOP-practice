package com.example.lab_8.mapper;

import com.example.lab_8.dto.CatalogueSectionDto;
import com.example.lab_8.model.CatalogueSection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CatalogueSectionMapper {

    @Mapping(source = "idSection", target = "id")
    @Mapping(source = "parent.idSection", target = "parentId")
    CatalogueSectionDto toDto(CatalogueSection section);

}