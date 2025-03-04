package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAll(Pageable pageable);
    List<Course> findByTitleContainingIgnoreCase(String title);

}
