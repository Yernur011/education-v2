package com.rdlab.education.service.crud.impl;

import com.rdlab.education.domain.entity.edu.ForumCategory;
import com.rdlab.education.domain.repository.edu.ForumCategoryRepository;
import com.rdlab.education.service.crud.ForumCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumCategoryServiceImpl implements ForumCategoryService {
    private final ForumCategoryRepository forumCategoryRepository;
    @Override
    public List<ForumCategory> findAll() {
        return forumCategoryRepository.findAll();
    }

}
