package com.legalDigital.education.service.business.logic;



import com.legalDigital.education.domain.dto.question.QuestionDto;
import com.legalDigital.education.domain.dto.test.TestAnswers;

import java.util.List;

public interface TestService {
    Boolean checkAnswer(TestAnswers testAnswers);
    Integer getScore(Long id, List<TestAnswers> questions);
    List<QuestionDto> startTest(Long id);
}
