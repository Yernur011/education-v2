package com.rdlab.education.domain.dto.material;

import com.rdlab.education.domain.enums.CourseStatus;
import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record MaterialDto(
        Long id,
        String title,
        String description,
        String image,
        List<Long> tags,
        CourseStatus status,
        ZoomDto zoom
) {
}
