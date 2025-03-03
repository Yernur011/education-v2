package com.rdlab.education.domain.dto.forum;

public record CreateQuestionDto(String title,
                                Long category,
                                String text,
                                String base64Image) {
}
