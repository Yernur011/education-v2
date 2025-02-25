package com.rdlab.education.domain.dto.lesson;

import lombok.Data;


@Data
public class LessonDto {
    public LessonDto(Long id, Long lessonNumber, String title, String videoUrl, String bodyText, String status, Boolean isCompleted) {
        this.id = id;
        this.lessonNumber = lessonNumber;
        this.title = title;
        this.videoUrl = videoUrl;
        this.bodyText = bodyText;
        this.status = status;
        this.isCompleted = isCompleted;
    }

    Long id;
    Long lessonNumber;
    String title;
    String videoUrl;
    String bodyText;
    String status;
    Boolean isCompleted;
}
