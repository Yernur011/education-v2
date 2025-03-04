package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.ForumAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumAnswerRepository extends JpaRepository<ForumAnswers, Long>  {
}
