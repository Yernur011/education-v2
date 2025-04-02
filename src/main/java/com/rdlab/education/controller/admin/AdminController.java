package com.rdlab.education.controller.admin;

import com.rdlab.education.domain.dto.course.AdminCourseResponse;
import com.rdlab.education.domain.dto.course.CourseDetailsDto;
import com.rdlab.education.domain.dto.forum.AdminForum;
import com.rdlab.education.domain.dto.lesson.LessonDto;
import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.test.TestCreateDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Tags;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.crud.CourseCrudService;
import com.rdlab.education.service.crud.ForumCrudService;
import com.rdlab.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import static com.rdlab.education.utils.codes.ProductCode.ADMIN_URI;
import static com.rdlab.education.utils.codes.ProductCode.ANSWERS_URI;
import static com.rdlab.education.utils.codes.ProductCode.COURSE_URI;
import static com.rdlab.education.utils.codes.ProductCode.FORUMS_URI;
import static com.rdlab.education.utils.codes.ProductCode.LESSON_URI;
import static com.rdlab.education.utils.codes.ProductCode.QUESTIONS_URI;
import static com.rdlab.education.utils.codes.ProductCode.TAGS_URI;
import static com.rdlab.education.utils.codes.ProductCode.TESTS_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;

@RestController
@RequestMapping(V1_URI + ADMIN_URI)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final CourseCrudService courseCrudService;
    private final CourseService courseService;
    private final ForumCrudService forumCrudService;
    private final TestCrudService testCrudService;

    //    Courses
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

    @DeleteMapping(COURSE_URI + "/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseCrudService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    //    Tags
    @PostMapping(COURSE_URI + TAGS_URI)
    public ResponseEntity<Tags> createTags(@RequestBody Tags request) {
        return ResponseEntity.ok(courseService.saveTags(request));
    }

    @DeleteMapping(COURSE_URI + TAGS_URI + "/{id}")
    public ResponseEntity<Void> deleteTags(@PathVariable Long id) {
        courseService.deleteTags(id);
        return ResponseEntity.ok().build();
    }

    //    Lesson
    @PutMapping(COURSE_URI + "/{courseId}" + LESSON_URI)
    public ResponseEntity<LessonDto> updateLesson(@PathVariable Long courseId, @RequestBody LessonDto request) {
        return ResponseEntity.ok(courseService.updateLesson(courseId, request));
    }

    @DeleteMapping(COURSE_URI + LESSON_URI + "/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        courseService.deleteLesson(id);
        return ResponseEntity.ok().build();
    }

    //    ForumQuestion
    @PostMapping(FORUMS_URI + "/approve")
    public ResponseEntity<Void> approveForum(@RequestBody Long id) {
        forumCrudService.approveForumQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(FORUMS_URI + "/revoke")
    public ResponseEntity<Void> revokeForum(@RequestBody Long id) {
        forumCrudService.revokeForumQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping(FORUMS_URI)
    public ResponseEntity<PageableDto<AdminForum>> getForums(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(forumCrudService.getAllForum(page.intValue(), size.intValue()));
    }

    @DeleteMapping(FORUMS_URI)
    public ResponseEntity<Void> deleteForum(@RequestBody Long id) {
        forumCrudService.removeForumQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    Tests
    @GetMapping(TESTS_URI)
    public ResponseEntity<PageableDto<TestDto>> getTests(@RequestParam Long page, @RequestParam Long size) {
        return ResponseEntity.ok(testCrudService.findAllTest(page, size));
    }
    @GetMapping(TESTS_URI + "/{id}")
    public ResponseEntity<TestCreateDto> getTestDetails(@PathVariable Long id){
        return ResponseEntity.ok(testCrudService.getTestDetails(id));
    }
    @DeleteMapping(TESTS_URI + "/{id}")
    public ResponseEntity<TestCreateDto> deleteTestDetails(@PathVariable Long id){
        testCrudService.deleteTest(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(TESTS_URI)
    public ResponseEntity<TestDto> createTest(@RequestBody TestCreateDto request) {
        return ResponseEntity.ok(testCrudService.save(request));
    }

    @PutMapping(TESTS_URI)
    public ResponseEntity<TestDto> updateTest(@RequestBody TestCreateDto request) {
        return ResponseEntity.ok(testCrudService.update(request));
    }

    //    question and answers
    @DeleteMapping(TESTS_URI +"/{tid}"+ QUESTIONS_URI + "/{qid}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("tid") Long testId,@PathVariable("qid") Long questionId) {
        testCrudService.deleteQuestion(testId, questionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(TESTS_URI+QUESTIONS_URI+"/{qid}" + ANSWERS_URI + "/{aid}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long qid, @PathVariable Long aid) {
        testCrudService.deleteAnswer(qid, aid);
        return ResponseEntity.ok().build();
    }

}
