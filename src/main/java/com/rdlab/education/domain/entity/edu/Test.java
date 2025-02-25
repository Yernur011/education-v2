package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.core.BusinessEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    @OneToOne(mappedBy = "test")
    private Course course;

}
