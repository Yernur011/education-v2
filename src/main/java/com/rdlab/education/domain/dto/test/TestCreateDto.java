package com.rdlab.education.domain.dto.test;

import com.rdlab.education.domain.dto.question.QuestionCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCreateDto {
    private Long id;
    private String title;
    private String state;
    private List<QuestionCreateDto> questions;
}
