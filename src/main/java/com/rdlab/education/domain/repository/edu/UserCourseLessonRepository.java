package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.UserCourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCourseLessonRepository extends JpaRepository<UserCourseLesson, Long> {
    List<UserCourseLesson> findByUserCourseId(Long userCourseId);
}
