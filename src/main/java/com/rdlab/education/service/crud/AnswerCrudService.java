package com.rdlab.education.service.crud;

import com.rdlab.education.domain.entity.edu.Answer;

public interface AnswerCrudService {
    Answer findAnswerById(Long answerId);
}
