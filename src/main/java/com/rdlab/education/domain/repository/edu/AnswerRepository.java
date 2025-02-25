package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAnswerByQuestionId(Long questionId);
}
