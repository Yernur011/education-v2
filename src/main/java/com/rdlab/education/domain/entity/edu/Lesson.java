package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.core.BusinessEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @Lob
    @Column(columnDefinition = "TEXT", name = "body_text")
    String bodyText;
    String status;
    @Column(name = "is_completed")
    Boolean isCompleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

}