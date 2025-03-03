package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.ForumCategory;
import com.rdlab.education.domain.entity.edu.ForumQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumQuestionRepository extends JpaRepository<ForumQuestion, Long> {

    Page<ForumQuestion> findForumQuestionByCategoryAndStatus(ForumCategory forumCategory, String status, Pageable pageable);

    Page<ForumQuestion> findForumQuestionByStatus(String status, Pageable pageable);

}
