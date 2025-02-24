package com.rdlab.education.domain.dto.test;

import lombok.*;
import lombok.experimental.FieldDefaults;


public class AnswerDto {
    Long id;
    String text;

    public AnswerDto() {
    }

    public AnswerDto(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
