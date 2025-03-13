package com.rdlab.education.domain.dto.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCreateDto {
    private Long id;
    private String text;
    private Boolean correct;
}
