package com.rdlab.education.domain.dto.forum;

public record CreateQuestionDto(String title,
                                Long tagId,
                                String text,
                                String base64Image) {
}
