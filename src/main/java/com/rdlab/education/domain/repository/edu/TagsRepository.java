package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagsRepository extends JpaRepository<Tags, Long> {
    Optional<Tags> findByName(String name);
}
