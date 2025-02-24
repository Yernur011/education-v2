package com.rdlab.education.service.crud;



import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.course.CoursesResponseDto;
import com.rdlab.education.domain.entity.edu.Course;

import java.util.List;

public interface CourseCrudService {
    List<CoursesResponseDto> findAll(Long page, Long size);
    List<Course> findAll();
    CourseDetailsDto findById(Long id);
    Course save(Course course);
    Course update(Course course);
    void deleteById(Long id);
}
