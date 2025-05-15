package com.legalDigital.education.service.business.logic.impl;

import com.legalDigital.education.domain.dto.question.QuestionDto;
import com.legalDigital.education.domain.dto.test.AnswerDto;
import com.legalDigital.education.domain.dto.test.TestAnswers;
import com.legalDigital.education.domain.entity.edu.Answer;
import com.legalDigital.education.domain.entity.edu.UserTest;
import com.legalDigital.education.domain.repository.edu.TestRepository;
import com.legalDigital.education.domain.repository.edu.UserTestRepository;
import com.legalDigital.education.service.auth.UserService;
import com.legalDigital.education.service.business.logic.TestService;
import com.legalDigital.education.service.crud.AnswerCrudService;
import com.legalDigital.education.service.crud.QuestionService;
import com.legalDigital.education.service.crud.TestCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.legalDigital.education.utils.codes.ErrorCode.TEST_NOT_FOUND;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestServiceImpl implements TestService {
    private final AnswerCrudService answerCrudService;
    private final TestCrudService testCrudService;
    private final QuestionService questionService;
    private final UserTestRepository userTestRepository;
    private final UserService userService;
    private final TestRepository testRepository;

    @Override
    public Boolean checkAnswer(TestAnswers testAnswers) {
        Long questionId = testAnswers.questionId();
        List<Long> answerId = testAnswers.answerIds();

        List<Answer> list = answerId.stream()
                .map(answerCrudService::findAnswerById)
                .toList();

        for (Answer answer : list) {
            if (!answer.getQuestion().getId().equals(questionId)) {
                throw new RuntimeException("Answer does not belong to the question");
            }
        }

        List<Boolean> avgPoints = list.stream().map(Answer::getCorrect)
                .filter(Boolean.FALSE::equals)
                .toList();//TODO calc avg points

        return avgPoints.isEmpty();
    }

    @Override
    public Integer getScore(Long id, List<TestAnswers> questions) {
        int correctAnswered = questions.stream()
                .map(this::checkAnswer)
                .mapToInt(ans -> ans ? 1 : 0)
                .sum();
        saveResults(id, correctAnswered);
        return correctAnswered;
    }

    private void saveResults(Long id, int correctAnswered) {
        userTestRepository.save(
                new UserTest(null,
                        userService.getCurrentUser(),
                        testRepository.findById(id).get(),
                        correctAnswered,
                        questionService.findQuestionsByTestId(id).size()
                ));
    }

    @Override
    public List<QuestionDto> startTest(Long id) {
        return testCrudService.findById(id)
                .map(test -> questionService.findQuestionsByTestId(test.getId())
                        .stream()
                        .map(question -> {
                            List<AnswerDto> ansDto =
                                    answerCrudService.findAnswerByQuestionId(question.getId())
                                            .stream()
                                            .map(answer ->
                                                    new AnswerDto(answer.getId(), answer.getText()))
                                            .toList();

                            return new QuestionDto(question.getId(),
                                    question.getQuestionNumber(),
                                    question.getText(),
                                    ansDto);
                        })
                        .toList())
                .orElseThrow(() -> new NoSuchElementException(TEST_NOT_FOUND));
    }


}
