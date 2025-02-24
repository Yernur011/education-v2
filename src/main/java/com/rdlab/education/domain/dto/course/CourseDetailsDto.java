package com.rdlab.education.domain.dto.course;

import com.rdlab.education.domain.entity.edu.Lesson;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


public class CourseDetailsDto {
    Long id;
    String title;
    String description;
    String image;
    List<String> tags = new ArrayList<>();
    List<Lesson> lessons = new ArrayList<>();

    public CourseDetailsDto() {
    }

    public CourseDetailsDto(Long id, String title, String description, String image, List<String> tags, List<Lesson> lessons) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.tags = tags;
        this.lessons = lessons;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
