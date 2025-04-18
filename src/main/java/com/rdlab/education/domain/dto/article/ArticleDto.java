package com.rdlab.education.domain.dto.article;

import com.rdlab.education.domain.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(toBuilder = true)
public record ArticleDto(
        Long id,

        @NotBlank(message = "Title is required")
        String title,

        String subTitle,

        String description,

        @NotNull(message = "Status is required")
        CourseStatus status,

        String file,

        String filename
) {
}