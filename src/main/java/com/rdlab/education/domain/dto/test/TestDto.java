package com.rdlab.education.domain.dto.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDto {
    Long id;
    String title;
    String state;
    String type;
    Long courseId;
    Long completedId;
    LocalDateTime completedAt;
    String createdDate;
}
