package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.entity.edu.Answer;
import com.rdlab.education.domain.repository.edu.AnswerRepository;
import com.rdlab.education.service.crud.AnswerCrudService;
import org.springframework.stereotype.Service;

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

}
