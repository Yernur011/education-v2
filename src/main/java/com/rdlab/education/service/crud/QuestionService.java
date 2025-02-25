package com.rdlab.education.service.crud;

import com.rdlab.education.domain.entity.edu.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findQuestionsByTestId(Long testId);
}
