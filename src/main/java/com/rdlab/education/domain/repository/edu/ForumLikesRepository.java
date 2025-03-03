package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.ForumLikes;
import com.rdlab.education.domain.entity.edu.ForumQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumLikesRepository extends JpaRepository<ForumLikes, Long> {
    boolean existsByUserIdAndForumId(Long userId, Long forumId);
    Integer countByForumId(Long forumId);
}
