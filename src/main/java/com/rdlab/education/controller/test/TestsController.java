package com.rdlab.education.controller.test;


import com.rdlab.education.domain.dto.question.QuestionDto;
import com.rdlab.education.domain.dto.test.TestAnswers;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.service.business.logic.TestService;
import com.rdlab.education.service.crud.TestCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.*;


@RestController
@RequestMapping(V1_URI + TESTS_URI)
public class TestsController {
    private final TestCrudService testCrudService;
    private final TestService testService;

    public TestsController(TestCrudService testCrudService, TestService testService) {
        this.testCrudService = testCrudService;
        this.testService = testService;
    }

    @GetMapping
    public ResponseEntity<List<TestDto>> tests(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(testCrudService.findAllTest(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTest(@PathVariable Long id) {
        return ResponseEntity.ok(testCrudService.findDtoById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<QuestionDto>> startTest(@PathVariable Long id) {
        return ResponseEntity.ok(testService.startTest(id));
    }
    @PostMapping(FINISH_URI)
    public ResponseEntity<Integer> getTestResult(@RequestBody List<TestAnswers> answers) {
        return ResponseEntity.ok(testService.getScore(answers));
    }

    //TODO дописать
    @GetMapping("/{id}" + USERS_URI)
    public ResponseEntity<Object> getTestById(@PathVariable Long id) {
        return null;
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
