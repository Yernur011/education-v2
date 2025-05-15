package com.legalDigital.education.domain.repository;

import com.legalDigital.education.domain.entity.image.Base64Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Base64ImagesRepository extends JpaRepository<Base64Images, Long> {
}
