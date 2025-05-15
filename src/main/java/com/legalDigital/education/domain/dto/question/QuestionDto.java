package com.legalDigital.education.domain.dto.question;

import com.legalDigital.education.domain.dto.test.AnswerDto;

import java.util.ArrayList;
import java.util.List;


public class QuestionDto {
    Long id;
    Long questionNumber;
    String text;
    List<AnswerDto> answers = new ArrayList<>();

    public QuestionDto() {
    }

    public QuestionDto(Long id, Long questionNumber, String text, List<AnswerDto> answers) {
        this.id = id;
        this.questionNumber = questionNumber;
        this.text = text;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Long questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
