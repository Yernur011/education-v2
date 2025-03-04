package com.rdlab.education.controller.maininfo;

import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.course.CoursesResponseDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.service.business.logic.TestService;
import com.rdlab.education.service.crud.CourseCrudService;
import com.rdlab.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.*;


@RestController
@RequestMapping(V1_URI + MAIN_INFO_URI)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainInfoController {

    private final CourseCrudService courseCrudService;
    private final TestCrudService testCrudService;
    private final TestService testService;

    @GetMapping(POPULAR_URI + COURSE_URI)
    private ResponseEntity<PageableDto<CoursesResponseDto>> popularCourse(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(courseCrudService.findAllPageable(page, size));
    }

    @GetMapping(POPULAR_URI + COURSE_URI + "/{id}")
    public ResponseEntity<CourseDetailsDto> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseCrudService.findById(id));
    }

    @GetMapping(POPULAR_URI + TESTS_URI)
    public ResponseEntity<PageableDto<TestDto>> tests(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(testCrudService.findAllTestDto(page, size));
    }

    @GetMapping(POPULAR_URI + TESTS_URI + "/{id}")
    public ResponseEntity<TestDto> getTest(@PathVariable Long id) {
        return ResponseEntity.ok(testCrudService.findDtoById(id));
    }

//    @GetMapping(POPULAR_URI + TESTS_URI)
//    private ResponseEntity<List<Object>> popularTests() {
//        return ResponseEntity.ok(List.of());
//    }

    @GetMapping(POPULAR_URI + QUESTIONS_URI)
    private ResponseEntity<List<Object>> popularQuestions() {
        return ResponseEntity.ok(List.of());
    }
}
