package com.rdlab.education.domain.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminCourseResponse {
    Long id;

    String title;

    LocalDateTime createdAt;

    List<String> tags = new ArrayList<>();

    String status;
}
