package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.entity.edu.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<LessonDto> findAllByCourseId(@Param("courseId") Long courseId);
}
