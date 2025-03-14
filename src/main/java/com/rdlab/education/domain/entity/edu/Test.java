package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.core.BusinessEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Test extends BusinessEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String state;
    String type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Question> questions = new ArrayList<>();

    @OneToOne(mappedBy = "test", cascade = CascadeType.ALL)
    private Course course;

    public Test(String title, String state, String type, List<Question> questions, Course course) {
        this.title = title;
        this.state = state;
        this.type = type;
        this.questions = questions;
        this.course = course;
    }
}
