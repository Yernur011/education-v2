package com.rdlab.education.service.business.logic;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Tags;

import java.util.List;

public interface CourseService {
    List<Tags> findAllTags();
    CourseDetailsDto findByIdWithoutProgress(Long id);
    CourseDetailsDto findByIdAndProgress(Long courseId);
    CourseDetailsDto startCourse(Long courseId, boolean isPaid);
    List<LessonDto> startLesson(Long lessonId);
    List<LessonDto> doneLesson(Long lessonId);
    TestDto getCurrentTestByCourse(Long courseId);
    void testFinished(Long id);
    CourseDetailsDto createCourse(CourseDetailsDto courseDetailsDto);
    Tags saveTags(Tags tags);
    void deleteTags(Long id);
    CourseDetailsDto updateCourse(CourseDetailsDto courseDetailsDto);
    LessonDto updateLesson(Long courseId,LessonDto lessonDto);
    void deleteLesson(Long lessonId);

    void payment(Long id);
}
