package com.rdlab.education.domain.dto.forum;

import java.time.LocalDateTime;


public record GetForums(
        Long questionId,
        String authorImage,
        String forumTitle,
        LocalDateTime createdAt,
        Integer answerCount,
        Integer likes) {

}
