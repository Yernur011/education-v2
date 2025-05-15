package com.legalDigital.education.service.crud;

import com.legalDigital.education.domain.dto.forum.ForumCategoryDto;

import java.util.List;

public interface ForumCategoryService {
    List<ForumCategoryDto> findAll();
}
