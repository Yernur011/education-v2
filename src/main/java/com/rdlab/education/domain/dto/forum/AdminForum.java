package com.rdlab.education.domain.dto.forum;

import java.time.LocalDateTime;

public record AdminForum (
        Long questionId,
        String authorImage,
        String forumTitle,
        LocalDateTime createdAt,
        Integer answerCount,
        Integer likes,
        String status) {

}