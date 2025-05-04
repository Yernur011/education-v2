package com.rdlab.education.domain.dto.forum;

import com.rdlab.education.domain.entity.edu.Tags;

import java.time.LocalDateTime;


public record GetForums(
        Long questionId,
        String authorImage,
        String forumTitle,
        LocalDateTime createdAt,
        Integer answerCount,
        Integer likes,
        Tags tags) {

}
