package com.legalDigital.education.domain.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
