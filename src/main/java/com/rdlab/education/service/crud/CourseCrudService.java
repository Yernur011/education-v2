package com.rdlab.education.service.crud;



import com.rdlab.education.domain.dto.course.AdminCourseResponse;
import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.course.CoursesResponseDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.Course;

public interface CourseCrudService {
    PageableDto<CoursesResponseDto> findAllPageable(Long page, Long size);
    PageableDto<AdminCourseResponse> findAll(Long page, Long size);
    PageableDto<CoursesResponseDto> getCouresHistory(Long page, Long size);
    CourseDetailsDto findByIdAndProgress(Long id);
    CourseDetailsDto findByIdWithoutProgress(Long id);
    CourseDetailsDto findById(Long id);
    Course save(Course course);
    Course update(Course course);
    void deleteById(Long id);

}
