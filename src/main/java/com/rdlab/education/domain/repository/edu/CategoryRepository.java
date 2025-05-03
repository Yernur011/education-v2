package com.rdlab.education.domain.repository.edu;

import com.rdlab.education.domain.entity.edu.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
