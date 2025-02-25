package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.entity.edu.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("SELECT new com.rdlab.education.domain.dto.LessonDto(" +
           "l.id, l.lessonNumber, l.title, l.videoUrl, l.bodyText, l.status, l.isCompleted) " +
           "FROM Lesson l WHERE l.course.id = :courseId")
    List<LessonDto> findAllByCourseId(@Param("courseId") Long courseId);
}
