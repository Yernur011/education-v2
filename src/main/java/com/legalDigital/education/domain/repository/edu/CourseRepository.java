package com.legalDigital.education.domain.repository.edu;

import com.legalDigital.education.domain.entity.edu.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findAll(Pageable pageable);

    Page<Course> findAllByStatus(String status, Pageable pageable);

    List<Course> findByTitleContainingIgnoreCase(String title);

    Optional<Course> findCourseByTest_Id(Long testId);

}
