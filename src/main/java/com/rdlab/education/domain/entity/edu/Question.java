package com.rdlab.education.domain.entity.edu;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long questionNumber;

    String text;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @OneToMany(cascade = CascadeType.ALL)
    List<Answer> answers = new ArrayList<>();

}
