package com.rdlab.education.controller;

import com.rdlab.education.service.business.logic.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rdlab.education.utils.codes.ProductCode.LESSON_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;

@RestController
@RequestMapping(V1_URI + LESSON_URI)
@RequiredArgsConstructor
public class LessonController {

    private final CourseService courseService;

    @PostMapping("{id}/start")
    public ResponseEntity<Object> startLesson(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.startLesson(id));
    }
    @PostMapping("{id}/finish")
    public ResponseEntity<Object> finishLesson(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.doneLesson(id));
    }

}
