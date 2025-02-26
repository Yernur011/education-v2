package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.core.BusinessEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lesson extends BusinessEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "lesson_number")
    Long lessonNumber;
    String title;
    @Column(name = "video_url")
    String videoUrl;
    @Column(columnDefinition = "TEXT", name = "body_text")
    String bodyText;
    String status;
    @Column(name = "is_completed")
    Boolean isCompleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

}