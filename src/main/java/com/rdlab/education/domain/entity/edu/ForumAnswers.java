package com.rdlab.education.domain.entity.edu;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForumAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String answerText;

    @OneToMany(cascade = CascadeType.ALL)
    List<ForumLikes> likes = new ArrayList<>();

}
