package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {
}
