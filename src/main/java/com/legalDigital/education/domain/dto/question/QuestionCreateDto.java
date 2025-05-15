package com.legalDigital.education.domain.dto.question;

import com.legalDigital.education.domain.dto.test.AnswerCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreateDto {
    private Long id;
    private Long questionNumber;
    private String text;
    private List<AnswerCreateDto> answers;
}
