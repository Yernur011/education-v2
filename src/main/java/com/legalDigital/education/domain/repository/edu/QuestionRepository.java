package com.legalDigital.education.domain.repository.edu;

import com.legalDigital.education.domain.entity.edu.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionsByTestId(Long testId);
}
