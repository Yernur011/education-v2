package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.image.Base64Images;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForumQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime createdAt = LocalDateTime.now();
    String status;
    String title;
    @ManyToOne
    ForumCategory category;
    @OneToOne(fetch = FetchType.LAZY)
    Base64Images images;
    String questionText;
    @ManyToOne
    Users author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    List<ForumAnswers> answers = new ArrayList<>();
}
