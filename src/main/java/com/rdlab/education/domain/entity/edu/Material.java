package com.rdlab.education.domain.entity.edu;

import com.rdlab.education.domain.entity.image.Base64Images;
import com.rdlab.education.domain.enums.CourseStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Base64Images image;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Tags> tags = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Zoom zoom;


}
