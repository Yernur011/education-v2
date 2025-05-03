package com.rdlab.education.service.crud;

import com.rdlab.education.domain.entity.edu.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Page<Category> findAll(int page, int size);
}
