package com.legalDigital.education.service.crud;

import com.legalDigital.education.domain.entity.edu.Answer;

import java.util.List;

public interface AnswerCrudService {
    Answer findAnswerById(Long answerId);

    List<Answer> findAnswerByQuestionId(Long questionId);
}
