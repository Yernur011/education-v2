package com.legalDigital.education.domain.entity.edu;

import com.legalDigital.education.domain.entity.auth.Users;
import com.legalDigital.education.domain.entity.image.Base64Images;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    Base64Images images;
    String questionText;
    @ManyToOne
    Users author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    List<ForumAnswers> answers = new ArrayList<>();
}
