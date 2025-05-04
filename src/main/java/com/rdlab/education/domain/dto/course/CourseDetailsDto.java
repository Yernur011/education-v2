package com.rdlab.education.domain.dto.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.dto.test.TestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDetailsDto {
    Long id;
    String title;
    String description;
    String image;
    Set<Long> tags = new HashSet<>();
    Set<Long> categories = new HashSet<>();
    List<LessonDto> lessons = new ArrayList<>();
    TestDto test;
    String status;
    String demoUrl;
}
