package com.rdlab.education.domain.dto.test;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDto {
    Long id;
    String title;
    String state;
    String type;
    Long courseId;
}
