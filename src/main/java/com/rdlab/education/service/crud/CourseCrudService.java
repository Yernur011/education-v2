package com.rdlab.education.service.crud;



import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.course.CoursesResponseDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.Course;

import java.util.List;

public interface CourseCrudService {
    List<CoursesResponseDto> findAll(Long page, Long size);
    PageableDto<CoursesResponseDto> findAllPageable(Long page, Long size);
    PageableDto<CoursesResponseDto> getCouresHistory(Long page, Long size);
    List<Course> findAll();
    CourseDetailsDto findByIdAndProgress(Long id);
    CourseDetailsDto findById(Long id);
    Course save(Course course);
    Course update(Course course);
    void deleteById(Long id);

}
