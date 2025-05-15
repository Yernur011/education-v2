package com.legalDigital.education.domain.entity.image;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base64images_id_seq")
    @SequenceGenerator(name = "base64images_id_seq", sequenceName = "base64images_id_seq", allocationSize = 1)
    Long id;
    String base64Image;

    public Base64Images(String base64Image) {
        this.base64Image = base64Image;
    }
}
