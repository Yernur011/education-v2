package com.rdlab.education.domain.mapper;

import com.rdlab.education.domain.dto.material.MaterialDto;
import com.rdlab.education.domain.entity.edu.Material;
import com.rdlab.education.domain.entity.edu.Tags;
import com.rdlab.education.domain.entity.image.Base64Images;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    @Mappings({
            @Mapping(source = "image", target = "image", qualifiedByName = "base64ToString"),
            @Mapping(source = "tags", target = "tags", qualifiedByName = "tagsToStrings")
    })
    MaterialDto toDto(Material entity);

    @Mappings({
            @Mapping(source = "image", target = "image", qualifiedByName = "stringToBase64"),
            @Mapping(source = "tags", target = "tags", qualifiedByName = "stringsToTags")
    })
    Material toEntity(MaterialDto dto);

    @Named("base64ToString")
    default String base64ToString(Base64Images base64Images) {
        return base64Images != null ? base64Images.getBase64Image() : null;
    }

    @Named("stringToBase64")
    default Base64Images stringToBase64(String base64) {
        return base64 != null ? new Base64Images(base64) : null;
    }

    @Named("tagsToStrings")
    default List<String> tagsToStrings(List<Tags> tags) {
        return tags != null ? tags.stream().map(Tags::getName).collect(Collectors.toList()) : null;
    }

    @Named("stringsToTags")
    default List<Tags> stringsToTags(List<String> names) {
        return names != null ? names.stream().map(Tags::new).collect(Collectors.toList()) : null;
    }
}