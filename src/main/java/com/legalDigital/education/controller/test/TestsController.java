package com.legalDigital.education.controller.test;


import com.legalDigital.education.domain.dto.page.PageableDto;
import com.legalDigital.education.domain.dto.question.QuestionDto;
import com.legalDigital.education.domain.dto.test.TestAnswers;
import com.legalDigital.education.domain.dto.test.TestDto;
import com.legalDigital.education.service.business.logic.CourseService;
import com.legalDigital.education.service.business.logic.TestService;
import com.legalDigital.education.service.crud.TestCrudService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import static com.legalDigital.education.utils.codes.ProductCode.TESTS_URI;
import static com.legalDigital.education.utils.codes.ProductCode.V1_URI;


@RestController
@RequestMapping(V1_URI + TESTS_URI)
public class TestsController {
    private final TestCrudService testCrudService;
    private final TestService testService;
    private final CourseService courseService;

    public TestsController(TestCrudService testCrudService, TestService testService, CourseService courseService) {
        this.testCrudService = testCrudService;
        this.testService = testService;
        this.courseService = courseService;
    }

    @GetMapping("history")
    public ResponseEntity<PageableDto<TestDto>> testHistory(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(testCrudService.findCompletedTestDto(page, size));
    }

    @GetMapping
    public ResponseEntity<PageableDto<TestDto>> tests(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(testCrudService.findAllTestDto(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTest(@PathVariable Long id) {
        return ResponseEntity.ok(testCrudService.findDtoById(id));
    }

    @PostMapping("/{id}/start") // todo check test
    public ResponseEntity<List<QuestionDto>> startTest(@PathVariable Long id) {
        return ResponseEntity.ok(testService.startTest(id));
    }

    @PostMapping("/{id}/finish") // todo check id and return like
    public ResponseEntity<String> getTestResult(@PathVariable Long id, @RequestBody List<TestAnswers> answers) {
        courseService.testFinished(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.ok()
                .headers(headers)
                .body("{\"correct\": " + testService.getScore(id, answers) + "}");

    }

    @PostMapping
    public ResponseEntity<Object> createTest(@RequestBody Object test) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Object> updateTest(@RequestBody Object test) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTest(@RequestBody Object test) {
        return null;
    }
}
