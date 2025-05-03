package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.entity.edu.Category;
import com.rdlab.education.domain.repository.edu.CategoryRepository;
import com.rdlab.education.service.crud.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Page<Category> findAll(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }
}
