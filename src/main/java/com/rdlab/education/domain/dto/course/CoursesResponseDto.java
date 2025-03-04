package com.rdlab.education.domain.dto.course;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoursesResponseDto {
    Long id;
    String image;
    List<String> tags = new ArrayList<>();
    String title;
    String description;
    String status;
}
