package com.rdlab.education.service.business.logic;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.lesson.LessonDto;

import java.util.List;

public interface CourseService {
    CourseDetailsDto findByIdAndProgress(Long courseId);
    CourseDetailsDto startCourse(Long courseId);
    List<LessonDto> startLesson(Long lessonId);
    List<LessonDto> doneLesson(Long lessonId);

}
