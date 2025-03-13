package com.rdlab.education.controller.course;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.course.CoursesResponseDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.entity.edu.Tags;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.crud.CourseCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.COURSE_URI;
import static com.rdlab.education.utils.codes.ProductCode.USERS_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;


@RestController
@RequestMapping(V1_URI + COURSE_URI)
@RequiredArgsConstructor
public class CourseController {
    private final CourseCrudService courseCrudService;
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<PageableDto<CoursesResponseDto>> getCourses(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(courseCrudService.findAllPageable(page, size));
    }

    @GetMapping("history")
    public ResponseEntity<PageableDto<CoursesResponseDto>> getCourseHistory(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(courseCrudService.getCouresHistory(page, size));
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tags>> getTags() {
        return ResponseEntity.ok(courseService.findAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailsDto> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseCrudService.findByIdAndProgress(id));
    }

    @GetMapping("/{id}" + USERS_URI)
    public ResponseEntity<List<Object>> getUsers(@RequestParam Long page, @RequestParam Long size, @PathVariable Long id) {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping("{id}/start")
    public ResponseEntity<Object> startCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.startCourse(id));
    }

    //    @PreAuthorize("moderator, admin") // todo
    @PostMapping
    public ResponseEntity<Object> createCourse(@RequestBody Object course) {
        return ResponseEntity.ok(List.of());
    }

    @PutMapping
    public ResponseEntity<Object> updateCourse(@RequestBody Object course) {
        return ResponseEntity.ok(List.of());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCourse(@RequestParam Long courseId) {
        courseCrudService.deleteById(courseId);
        return ResponseEntity.ok().build();
    }

}
