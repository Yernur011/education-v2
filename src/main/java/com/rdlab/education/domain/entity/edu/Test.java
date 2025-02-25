package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.core.BusinessEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Test extends BusinessEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String state;
    String type;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Question> questions = new ArrayList<>();

    public Test() {
    }

    public Test(String title, Long id, String state, String type, List<Question> questions) {
        this.title = title;
        this.id = id;
        this.state = state;
        this.type = type;
        this.questions = questions;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
