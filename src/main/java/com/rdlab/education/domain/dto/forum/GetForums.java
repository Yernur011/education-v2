package com.rdlab.education.domain.dto.forum;

import java.time.LocalDateTime;


public record GetForums(
        Long questionId,
        String userImage,
        String title,
        LocalDateTime createdAt,
        Integer answerCount,
        Integer likes) {

}
