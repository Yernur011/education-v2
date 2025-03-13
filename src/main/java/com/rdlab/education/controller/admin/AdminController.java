package com.rdlab.education.controller.admin;

import com.rdlab.education.domain.dto.course.AdminCourseResponse;
import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.Course;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.crud.CourseCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.rdlab.education.utils.codes.ProductCode.*;

@RestController
@RequestMapping(V1_URI + ADMIN_URI)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final CourseCrudService courseCrudService;
    private final CourseService courseService;

    @GetMapping(COURSE_URI)
    public ResponseEntity<PageableDto<AdminCourseResponse>> getCourses(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(courseCrudService.findAll(page, size));
    }

    @PostMapping(COURSE_URI)
    public ResponseEntity<CourseDetailsDto> createCourse(@RequestBody CourseDetailsDto request) {
        return ResponseEntity.ok(courseService.createCourse(request));
    }

    @PutMapping(COURSE_URI)
    public ResponseEntity<CourseDetailsDto> updateCourse(@RequestBody CourseDetailsDto request) {
        return ResponseEntity.ok(courseService.updateCourse(request));
    }

    @GetMapping(COURSE_URI + "/{id}")
    public ResponseEntity<CourseDetailsDto> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseCrudService.findById(id));
    }

}
