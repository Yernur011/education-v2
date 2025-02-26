package com.rdlab.education.service.business.logic.impl;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.entity.edu.Lesson;
import com.rdlab.education.domain.entity.edu.UserCourse;
import com.rdlab.education.domain.entity.edu.UserCourseLesson;
import com.rdlab.education.domain.enums.UserCourseLessonStatusEnum;
import com.rdlab.education.domain.exceptions.NotFoundException;
import com.rdlab.education.domain.repository.edu.CourseRepository;
import com.rdlab.education.domain.repository.edu.LessonRepository;
import com.rdlab.education.domain.repository.edu.UserCourseLessonRepository;
import com.rdlab.education.domain.repository.edu.UserCourseRepository;
import com.rdlab.education.service.auth.UserService;
import com.rdlab.education.service.business.logic.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;
    private final UserCourseLessonRepository userCourseLessonRepository;
    private final LessonRepository lessonRepository;
    private final UserService userService;

    @Override
    public CourseDetailsDto startCourse(Long courseId) {
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course Not Found"));
        userCourseRepository.save(new UserCourse(null, userService.getCurrentUser(), course));

        startLesson(course.getLessons()
                .stream()
                .sorted((l1, l2) -> l1.getLessonNumber().compareTo(l2.getLessonNumber()))
                .limit(1)
                .toList().getFirst().getId());
        return new CourseDetailsDto();
    }

    @Override
    public LessonDto startLesson(Long lessonId) {
        var lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson Not Found"));
        userCourseLessonRepository.save(
                new UserCourseLesson(null,
                        findUserCourse(lesson),
                        lesson, userService.getCurrentUser(),
                        UserCourseLessonStatusEnum.ACTIVE.getStatus()));
        return new LessonDto();
    }

    private UserCourse findUserCourse(Lesson lesson) {
        return userCourseRepository.findByCourseAndUser(lesson.getCourse(), userService.getCurrentUser());
    }


}
