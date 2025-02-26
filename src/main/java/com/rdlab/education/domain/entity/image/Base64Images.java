package com.rdlab.education.domain.entity.image;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Base64Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String base64Image;
}
