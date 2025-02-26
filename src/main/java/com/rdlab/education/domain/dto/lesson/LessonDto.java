package com.rdlab.education.domain.dto.lesson;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonDto {
    Long id;
    Long lessonNumber;
    String title;
    String videoUrl;
    String bodyText;
    String status;
    Boolean isCompleted;
}
