package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.entity.edu.Question;
import com.rdlab.education.domain.repository.edu.QuestionRepository;
import com.rdlab.education.service.crud.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> findQuestionsByTestId(Long testId) {
        return questionRepository.findQuestionsByTestId(testId);
    }
}
