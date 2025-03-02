package com.rdlab.education.domain.dto.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDto {
    Long id;
    String title;
    String state;
    String type;
    Long courseId;
    Long completedId;
    LocalDateTime completedAt;
}
