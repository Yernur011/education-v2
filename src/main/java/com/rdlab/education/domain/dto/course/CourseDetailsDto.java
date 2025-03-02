package com.rdlab.education.domain.dto.course;

import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.dto.test.TestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDetailsDto {
    Long id;
    String title;
    String description;
    String image;
    List<String> tags = new ArrayList<>();
    List<LessonDto> lessons = new ArrayList<>();
    TestDto test;
    String status;
}
