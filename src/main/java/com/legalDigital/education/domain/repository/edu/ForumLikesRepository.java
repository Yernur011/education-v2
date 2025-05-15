package com.legalDigital.education.domain.repository.edu;

import com.legalDigital.education.domain.entity.edu.ForumLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumLikesRepository extends JpaRepository<ForumLikes, Long> {
    boolean existsByUserIdAndForumId(Long userId, Long forumId);
    Integer countByForumId(Long forumId);
}
