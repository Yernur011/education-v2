package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.core.BusinessEntity;
import com.rdlab.education.domain.entity.image.Base64Images;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Course extends BusinessEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Base64Images base64Images;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Test test;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    List<Tags> tags = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY, mappedBy = "course")
    List<Lesson> lessons = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    List<Users> users = new ArrayList<>();

}
