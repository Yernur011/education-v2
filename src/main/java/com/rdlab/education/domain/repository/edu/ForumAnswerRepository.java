package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.edu.ForumAnswers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumAnswerRepository extends JpaRepository<ForumAnswers, Long> {
    Page<ForumAnswers> findByUser(Users user, Pageable pageable);

    List<ForumAnswers> findByAnswerTextContainingIgnoreCase(String text);

}
