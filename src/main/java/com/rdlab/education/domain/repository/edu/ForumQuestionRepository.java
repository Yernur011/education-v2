package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.edu.ForumCategory;
import com.rdlab.education.domain.entity.edu.ForumQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ForumQuestionRepository extends JpaRepository<ForumQuestion, Long> {

    Page<ForumQuestion> findForumQuestionByCategoryAndStatus(ForumCategory forumCategory, String status, Pageable pageable);

    @Query("SELECT COUNT(q) FROM ForumQuestion q WHERE q.category.id = :categoryId and q.status = :status")
    Long countQuestionsByCategoryId(@Param("categoryId") Long categoryId, String status);

    Page<ForumQuestion> findForumQuestionByStatus(String status, Pageable pageable);

    Page<ForumQuestion> findForumQuestionByAuthor(Users author, Pageable pageable);

    List<ForumQuestion> findByTitleContainingIgnoreCaseOrQuestionTextContainingIgnoreCase(String title, String text);

}
