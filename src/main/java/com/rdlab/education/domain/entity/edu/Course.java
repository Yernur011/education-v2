package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.auth.Users;
import com.rdlab.education.domain.entity.core.BusinessEntity;
import com.rdlab.education.domain.entity.image.Base64Images;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course extends BusinessEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Base64Images base64Images;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    Test test;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_tags",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "tags_id"})
    )
    Set<Tags> tags = new HashSet<>();

//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
//    List<Category> categories = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "course_forum_category",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "forum_category_id")
    )
    Set<Category> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
    Set<Lesson> lessons = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    List<Users> users = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    Collection<UserCourse> userCourse;

    @Column(name = "course_status", nullable = false)
    String status;

    String demoUrl;
}
