package com.legalDigital.education.service.crud.impl;

import com.legalDigital.education.domain.entity.edu.Answer;
import com.legalDigital.education.domain.repository.edu.AnswerRepository;
import com.legalDigital.education.service.crud.AnswerCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerCrudServiceImpl implements AnswerCrudService {
    private final AnswerRepository answerRepository;

    public AnswerCrudServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer findAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }

    @Override
    public List<Answer> findAnswerByQuestionId(Long questionId) {
        return answerRepository.findAnswerByQuestionId(questionId);
    }

}
