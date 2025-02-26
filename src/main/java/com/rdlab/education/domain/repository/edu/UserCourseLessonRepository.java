package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.UserCourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCourseLessonRepository extends JpaRepository<UserCourseLesson, Long> {
}
