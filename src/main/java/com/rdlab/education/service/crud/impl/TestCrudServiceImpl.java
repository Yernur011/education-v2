package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.question.QuestionCreateDto;
import com.rdlab.education.domain.dto.test.AnswerCreateDto;
import com.rdlab.education.domain.dto.test.TestCreateDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.*;
import com.rdlab.education.domain.enums.UserCourseLessonStatusEnum;
import com.rdlab.education.domain.exceptions.ApiException;
import com.rdlab.education.domain.repository.edu.*;
import com.rdlab.education.service.auth.UserService;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.rdlab.education.utils.codes.ErrorCode.COURSE_NOT_FOUND;
import static com.rdlab.education.utils.codes.ErrorCode.TEST_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class TestCrudServiceImpl implements TestCrudService {
    private final TestRepository testRepository;
    private final CourseService courseService;
    private final UserTestRepository userTestRepository;
    private final UserService userService;
    private final CourseRepository courseRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;


    @Override
    public PageableDto<TestDto> findAllTest(Long page, Long size) {

        Page<Test> all = testRepository.findAll(PageRequest.of(page.intValue(), size.intValue()));
        List<TestDto> list = all.getContent()
                .stream()
                .map(test ->
                        {
                            Course course = courseRepository.findCourseByTest_Id(test.getId()).orElse(null);
                            return new TestDto(
                                    test.getId(),
                                    test.getTitle(),
                                    test.getState(),
                                    test.getType(),
                                    course == null ? null : course.getId(),
                                    null,
                                    null,
                                    test.getCreationDate().toString()
                            );
                        }
                )
                .toList();

        return new PageableDto<>(all.getTotalPages(), list);
    }

    @Override
    public PageableDto<TestDto> findAllTestDto(Long page, Long size) {
        PageableDto<TestDto> pageableDto = new PageableDto<>();
        Page<Test> all = testRepository.findAll(PageRequest.of(page.intValue(), size.intValue()));

        pageableDto.setTotalPages(all.getTotalPages());
        pageableDto.setContent(all.getContent()
                .stream()
                .filter(test -> test.getCourse() != null)
                .map(test -> courseService.getCurrentTestByCourse(test.getCourse().getId()))
                .toList());

        return pageableDto;
    }

    @Override
    public PageableDto<TestDto> findCompletedTestDto(Long page, Long size) {
        PageableDto<TestDto> pageableDto = new PageableDto<>();
        Page<UserTest> all = userTestRepository.
                findAllByUserId(userService.getCurrentUser().getId(), PageRequest.of(page.intValue(), size.intValue()));
        pageableDto.setTotalPages(all.getTotalPages());
        pageableDto.setContent(all.getContent()
                .stream()
                .map(userTest -> new TestDto(
                                userTest.getTest().getId(),
                                userTest.getTest().getTitle(),
                                UserCourseLessonStatusEnum.COMPLETED.getStatus(),
                                userTest.getTest().getType(),
                                userTest.getTest().getCourse().getId(),
                                userTest.getId(),
                                userTest.getCreationDate(),
                                null
                        )
                )
                .toList());
        return pageableDto;
    }

    @Override
    public TestDto findDtoById(Long id) {
        return testRepository.findById(id)
                .map(test -> courseService.getCurrentTestByCourse(test.getCourse().getId()))
                .orElseThrow(() -> new NoSuchElementException(TEST_NOT_FOUND));
    }

    @Override
    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }

    @Override
    @Transactional
    public TestDto save(TestCreateDto testDto) {
        Course course = courseRepository.findById(testDto.getCourseId()).orElseThrow(() -> new ApiException(COURSE_NOT_FOUND));
        Test build = Test.builder()
                .title(testDto.getTitle())
                .state(testDto.getState())
                .type(testDto.getType())
                .course(course)
                .build();

        List<Question> list = testDto.getQuestions().stream()
                .map(questionCreateDto -> {

                    Question question = new Question(
                            null,
                            questionCreateDto.getQuestionNumber(),
                            questionCreateDto.getText(),
                            build,
                            null);

                    List<Answer> list1 = questionCreateDto.getAnswers()
                            .stream()
                            .map(answerCreateDto ->
                                    new Answer(
                                            null,
                                            answerCreateDto.getText(),
                                            answerCreateDto.getCorrect(),
                                            question))
                            .toList();
                    question.setAnswers(list1);
                    return question;
                })
                .toList();

        build.setQuestions(list);
        course.setTest(build);

        courseRepository.save(course);
        Test save = testRepository.save(build);


        return new TestDto(
                save.getId(),
                save.getTitle(),
                save.getState(),
                save.getType(),
                save.getCourse().getId(),
                null,
                null,
                save.getCreationDate().toString());
    }

    @Override
    @Transactional
    public TestDto update(TestCreateDto testDto) {
        Course course = courseRepository.findById(testDto.getCourseId()).orElseThrow(() -> new ApiException(COURSE_NOT_FOUND));
        Test build = Test.builder()
                .id(testDto.getId())
                .title(testDto.getTitle())
                .state(testDto.getState())
                .type(testDto.getType())
                .course(courseRepository.findById(testDto.getCourseId()).orElseThrow(() -> new NoSuchElementException(TEST_NOT_FOUND)))
                .build();

        List<Question> list = testDto.getQuestions().stream()
                .map(questionCreateDto -> {

                    Question question = new Question(
                            questionCreateDto.getId(),
                            questionCreateDto.getQuestionNumber(),
                            questionCreateDto.getText(),
                            build,
                            null);

                    List<Answer> list1 = questionCreateDto.getAnswers()
                            .stream()
                            .map(answerCreateDto ->
                                    new Answer(
                                            answerCreateDto.getId(),
                                            answerCreateDto.getText(),
                                            answerCreateDto.getCorrect(),
                                            question))
                            .toList();
                    question.setAnswers(list1);
                    return question;
                })
                .toList();

        build.setQuestions(list);
        course.setTest(build);

        Test save = testRepository.save(build);
        courseRepository.save(course);

        return new TestDto(
                save.getId(),
                save.getTitle(),
                save.getState(),
                save.getType(),
                save.getCourse().getId(),
                null,
                null,
                save.getCreationDate() == null ? null : save.getCreationDate().toString());
    }

    @Override
    public TestCreateDto getTestDetails(Long id) {

        return testRepository.findById(id)
                .map(test -> {
                    Course course = courseRepository.findCourseByTest_Id(test.getId()).orElse(null);
                    return new TestCreateDto(
                            test.getId(),
                            test.getTitle(),
                            test.getState(),
                            course == null ? null : course.getId(),
                            test.getType(),
                            test.getQuestions()
                                    .stream()
                                    .map(question ->
                                            new QuestionCreateDto(
                                                    question.getId(),
                                                    question.getQuestionNumber(),
                                                    question.getText(),
                                                    question.getAnswers()
                                                            .stream()
                                                            .map(answer ->
                                                                    new AnswerCreateDto(
                                                                            answer.getId(),
                                                                            answer.getText(),
                                                                            answer.getCorrect()
                                                                    ))
                                                            .toList()))
                                    .toList());})
                .orElseThrow(() -> new ApiException(TEST_NOT_FOUND));
    }

    @Override
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteQuestion(Long testId, Long questionId) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new NoSuchElementException("Тест не найден"));

        Question questionToRemove = test.getQuestions().stream()
                .filter(q -> q.getId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Вопрос не найден"));

        test.getQuestions().remove(questionToRemove);

        testRepository.save(test);

    }

    @Override
    @Transactional
    public void deleteAnswer(Long questionId, Long answerId) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        Answer answerToRemove = question.getAnswers().stream()
                .filter(a -> a.getId().equals(answerId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Ответ не найден"));

        question.getAnswers().remove(answerToRemove);

        questionRepository.save(question);
    }
}
