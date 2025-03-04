package com.rdlab.education.service.crud;

import com.rdlab.education.domain.dto.forum.ForumCategoryDto;
import com.rdlab.education.domain.entity.edu.ForumCategory;

import java.util.List;

public interface ForumCategoryService {
    List<ForumCategoryDto> findAll();
}
