package com.rdlab.education.service.business.logic;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.lesson.LessonDto;

public interface CourseService {
    CourseDetailsDto findByIdAndProgress(Long courseId);
    CourseDetailsDto startCourse(Long courseId);
    LessonDto startLesson(Long lessonId);

}
