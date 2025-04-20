package com.rdlab.education.domain.mapper;

import com.rdlab.education.domain.dto.material.ZoomDto;
import com.rdlab.education.domain.entity.edu.Zoom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ZoomMapper {
    ZoomMapper INSTANCE = Mappers.getMapper(ZoomMapper.class);

    ZoomDto toDto(Zoom entity);

    Zoom toEntity(ZoomDto dto);
}