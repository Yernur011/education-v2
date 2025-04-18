package com.rdlab.education.domain.mapper;

import com.rdlab.education.domain.dto.article.ArticleDto;
import com.rdlab.education.domain.entity.edu.Articles;
import com.rdlab.education.domain.entity.image.Base64Images;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mappings({
            @Mapping(source = "file", target = "file", qualifiedByName = "base64ToString")
    })
    ArticleDto toDto(Articles entity);

    @Mappings({
            @Mapping(source = "file", target = "file", qualifiedByName = "stringToBase64")
    })
    Articles toEntity(ArticleDto dto);

    @Named("base64ToString")
    default String base64ToString(Base64Images base64Images) {
        return base64Images != null ? base64Images.getBase64Image() : null;
    }

    @Named("stringToBase64")
    default Base64Images stringToBase64(String base64) {
        return base64 != null ? new Base64Images(base64) : null;
    }

}