package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.dto.page.PageableDto;
import com.rdlab.education.domain.dto.test.TestCreateDto;
import com.rdlab.education.domain.dto.test.TestDto;
import com.rdlab.education.domain.entity.edu.Answer;
import com.rdlab.education.domain.entity.edu.Question;
import com.rdlab.education.domain.entity.edu.Test;
import com.rdlab.education.domain.entity.edu.UserTest;
import com.rdlab.education.domain.enums.UserCourseLessonStatusEnum;
import com.rdlab.education.domain.repository.edu.CourseRepository;
import com.rdlab.education.domain.repository.edu.TestRepository;
import com.rdlab.education.domain.repository.edu.UserTestRepository;
import com.rdlab.education.service.auth.UserService;
import com.rdlab.education.service.business.logic.CourseService;
import com.rdlab.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.rdlab.education.utils.codes.ErrorCode.TEST_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class TestCrudServiceImpl implements TestCrudService {
    private final TestRepository testRepository;
    private final CourseService courseService;
    private final UserTestRepository userTestRepository;
    private final UserService userService;
    private final CourseRepository courseRepository;


    @Override
    public PageableDto<TestDto> findAllTest(Long page, Long size) {

        Page<Test> all = testRepository.findAll(PageRequest.of(page.intValue(), size.intValue()));
        List<TestDto> list = all.getContent()
                .stream()
                .map(test ->
                        new TestDto(
                                test.getId(),
                                test.getTitle(),
                                test.getState(),
                                test.getType(),
                                test.getCourse().getId(),
                                null,
                                null,
                                test.getCreationDate().toString()
                        )
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
    public TestDto save(TestCreateDto testDto) {
//
//        List<Question> questions = new ArrayList<>();
//        List<Question> list = testDto.getQuestions().stream()
//                .map(questionCreateDto ->
//                        new Question(
//                                null,
//                                questionCreateDto.getQuestionNumber(),
//                                questionCreateDto.getText(),
//                                null,
//                                questionCreateDto.getAnswers()
//                                        .stream()
//                                        .map(answerCreateDto ->
//                                                new Answer(
//                                                        null,
//                                                        answerCreateDto.getText(),
//                                                        answerCreateDto.getCorrect(),
//                                                        null))
//                                        .toList())).toList();
//
//        testRepository.save(Test.builder()
//                        .title(testDto.getTitle())
//                        .state(testDto.getState())
//                        .type(testDto.getType())
//                        .course(courseRepository.findById(testDto.getCourseId()).orElseThrow(() -> new NoSuchElementException(TEST_NOT_FOUND)))
//                        .questions()
//                .build());

        return new TestDto();
    }


}
