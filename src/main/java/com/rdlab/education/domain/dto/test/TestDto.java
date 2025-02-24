package com.rdlab.education.domain.dto.test;

import lombok.*;
import lombok.experimental.FieldDefaults;

public class TestDto {
    Long id;
    String title;
    String state;
    String type;

    public TestDto() {
    }

    public TestDto(Long id, String title, String state, String type) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

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
}
