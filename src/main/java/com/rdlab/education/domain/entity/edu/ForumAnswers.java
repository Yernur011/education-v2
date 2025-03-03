package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.auth.Users;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForumAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "question_id") // Добавляем внешний ключ к ForumQuestion
    ForumQuestion question;

    @ManyToOne
    Users user;

    String answerText;

    LocalDateTime createdDate = LocalDateTime.now();
}
