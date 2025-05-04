package com.rdlab.education.controller.maininfo;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.course.CoursesResponseDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Category;
import com.rdlab.education.service.crud.CategoryService;
import com.rdlab.education.service.crud.CourseCrudService;
import com.rdlab.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.COURSE_URI;
import static com.rdlab.education.utils.codes.ProductCode.MAIN_INFO_URI;
import static com.rdlab.education.utils.codes.ProductCode.POPULAR_URI;
import static com.rdlab.education.utils.codes.ProductCode.QUESTIONS_URI;
import static com.rdlab.education.utils.codes.ProductCode.TESTS_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;


@RestController
@RequestMapping(V1_URI + MAIN_INFO_URI)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainInfoController {

    private final CourseCrudService courseCrudService;
    private final TestCrudService testCrudService;
    private final CategoryService categoryService;

    @GetMapping(POPULAR_URI + COURSE_URI)
    private ResponseEntity<PageableDto<CoursesResponseDto>> popularCourse(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(courseCrudService.findAllPageable(page, size));
    }

    @GetMapping(POPULAR_URI + COURSE_URI + "/{id}")
    public ResponseEntity<CourseDetailsDto> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseCrudService.findByIdWithoutProgress(id));
    }

    @GetMapping(POPULAR_URI + TESTS_URI)
    public ResponseEntity<PageableDto<TestDto>> tests(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(testCrudService.findAllTestDto(page, size));
    }

    @GetMapping(POPULAR_URI + TESTS_URI + "/{id}")
    public ResponseEntity<TestDto> getTest(@PathVariable Long id) {
        return ResponseEntity.ok(testCrudService.findDtoById(id));
    }

    @GetMapping(POPULAR_URI + QUESTIONS_URI)
    private ResponseEntity<List<Object>> popularQuestions() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping(POPULAR_URI + "/category")
    private ResponseEntity<Page<Category>> popularUsers(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(categoryService.findAll(page, size));
    }
}
