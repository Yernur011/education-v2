package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.UserCourse;
import com.rdlab.education.domain.entity.edu.UserCourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCourseLessonRepository extends JpaRepository<UserCourseLesson, Long> {
    List<UserCourseLesson> findByUserCourseId(Long userCourseId);
    Optional<UserCourseLesson> findByLessonId(Long lessonId);
    UserCourseLesson findByLessonIdAndUserCourse(Long lessonId, UserCourse userCourse);
}
