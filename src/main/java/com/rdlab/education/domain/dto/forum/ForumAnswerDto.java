package com.rdlab.education.domain.dto.forum;

import com.rdlab.education.domain.dto.user.info.UserInfoOutputDto;

import java.time.LocalDateTime;

public record ForumAnswerDto(String text, LocalDateTime createdDate, UserInfoOutputDto user) {
    
}
