package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.core.BusinessEntity;
import jakarta.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;
    Boolean correct;

    @ManyToOne
    private Question question;

    public Answer() {
    }

    public Answer(Long id, String text, Boolean correct, Question question) {
        this.id = id;
        this.text = text;
        this.correct = correct;
        this.question = question;
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

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
