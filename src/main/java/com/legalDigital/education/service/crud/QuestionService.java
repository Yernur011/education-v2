package com.legalDigital.education.service.crud;

import com.legalDigital.education.domain.entity.edu.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findQuestionsByTestId(Long testId);
}
